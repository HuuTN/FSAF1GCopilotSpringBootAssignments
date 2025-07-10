package com.example.lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Enable this feature
public class lab4SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(lab4SpringbootApplication.class, args);
    }
}
