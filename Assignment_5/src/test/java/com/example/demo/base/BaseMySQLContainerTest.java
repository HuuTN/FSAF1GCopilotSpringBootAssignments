package com.example.demo.base;

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
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.33")
            .withDatabaseName("db_name")
            .withUsername("user_name")
            .withPassword("user_pwd");

    @DynamicPropertySource
    @SuppressWarnings("unused")
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
    }

    @BeforeAll
    @SuppressWarnings("unused")
    static void startContainer() {
        mysql.start();
    }

    @AfterAll
    @SuppressWarnings("unused")
    static void stopContainer() {
        mysql.stop();
    }
}
