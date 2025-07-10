package com.example.lab3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseIntegrationTest {
    // @Container
    // public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.36")
    //         .withDatabaseName("testdb")
    //         .withUsername("test")
    //         .withPassword("test");

    @BeforeAll
    void setUp() {
        // System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        // System.setProperty("spring.datasource.username", mysql.getUsername());
        // System.setProperty("spring.datasource.password", mysql.getPassword());
    }
}
