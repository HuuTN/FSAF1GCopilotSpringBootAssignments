package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;
// ...existing imports...
@SpringBootTest
public abstract class BaseIntegrationTest {
    // No Testcontainers. Use local MySQL for integration tests.
}
