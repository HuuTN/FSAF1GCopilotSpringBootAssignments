package com.example.lab3.controller;

import com.example.lab3.service.OrderItemService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OrderItemController.class)
class OrderItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderItemService orderItemService;

    @Test
    void testContextLoads() {
        assertNotNull(mockMvc);
    }
}
