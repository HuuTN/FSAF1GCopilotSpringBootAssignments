package com.example.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class BaseMySQLContainerTest {
    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.41")
            .withDatabaseName("shoppingcart")
            .withUsername("root")
            .withPassword("nguyenDeo@19992000!");

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
    }

    @BeforeAll
    static void startContainer() {
        mysql.start();
    }

    @AfterAll
    static void stopContainer() {
        mysql.stop();
    }
}
