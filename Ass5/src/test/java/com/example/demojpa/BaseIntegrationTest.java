package com.example.demojpa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseIntegrationTest {
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.36")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    static {
        mysql.start();
        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());
    }
}
