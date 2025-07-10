package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Configuration
@EnableConfigurationProperties({AppProperties.class, SwaggerProperties.class, DatasourceProperties.class})
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "feature.enabled", havingValue = "true")
    public FeatureService featureService() {
        return new FeatureService();
    }

    @Bean
    @ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
    public SwaggerConfig swaggerConfig() {
        return new SwaggerConfig();
    }

    @Bean
    @ConditionalOnProperty(name = "datasource.enabled", havingValue = "true")
    public DatasourceConfig datasourceConfig() {
        return new DatasourceConfig();
    }
}

@ConfigurationProperties(prefix = "app")
class AppProperties {
    private String name;
    private String version;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}

@ConfigurationProperties(prefix = "swagger")
class SwaggerProperties {
    private boolean enabled;
    private String title;
    private String description;

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

@ConfigurationProperties(prefix = "datasource")
class DatasourceProperties {
    private boolean enabled;
    private String url;
    private String username;
    private String password;

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

class FeatureService {
    // Feature-specific logic here
}

class SwaggerConfig {
    // Swagger-specific logic here
}

class DatasourceConfig {
    // Datasource-specific logic here
}
