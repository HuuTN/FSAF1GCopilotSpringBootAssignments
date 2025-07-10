package com.example.lab3.config;

// Swagger configuration with conditional bean
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    @ConditionalOnProperty(name = "swagger.enabled", havingValue = "true", matchIfMissing = true)
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("API Docs").version("v1"));
    }
}
