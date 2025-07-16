package com.example.lab4.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lab4.service.serviceimpl.OrderItemServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderItemServiceImpl orderItemService;

    @Test
    void testContextLoads() {
        assertNotNull(mockMvc);
    }
}
