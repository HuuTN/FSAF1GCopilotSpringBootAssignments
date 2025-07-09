package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

@Configuration
public class SpringFoxHandlerProviderBeanPostProcessor {

    @Autowired(required = false)
    private List<WebMvcRequestHandlerProvider> providers;

    @PostConstruct
    public void init() {
        if (providers != null) {
            for (WebMvcRequestHandlerProvider provider : providers) {
                customizeSpringfoxHandlerMappings(getHandlerMappings(provider));
            }
        }
    }

    private List<RequestMappingHandlerMapping> getHandlerMappings(WebMvcRequestHandlerProvider provider) {
        try {
            Field field = WebMvcRequestHandlerProvider.class.getDeclaredField("handlerMappings");
            field.setAccessible(true);
            return (List<RequestMappingHandlerMapping>) field.get(provider);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private void customizeSpringfoxHandlerMappings(List<RequestMappingHandlerMapping> mappings) {
        List<RequestMappingHandlerMapping> copy = mappings.stream()
                .filter(mapping -> mapping.getPatternParser() == null)
                .collect(Collectors.toList());
        mappings.clear();
        mappings.addAll(copy);
    }
}