package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ECommerceWorkflowTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void endToEnd_placeOrder() throws Exception {
        // 0. Create Category (required for Product)
        Map<String, Object> categoryDto = Map.of(
                "name", "Books"
        );
        MvcResult categoryResult = mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isCreated())
                .andReturn();
        Map<?,?> categoryResp = objectMapper.readValue(categoryResult.getResponse().getContentAsString(), Map.class);
        Long categoryId = Long.valueOf(categoryResp.get("id").toString());

        // 1. Create User
        Map<String, Object> userDto = Map.of(
                "name", "Alice",
                "email", "alice@example.com"
        );
        MvcResult userResult = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andReturn();
        Map<?,?> userResp = objectMapper.readValue(userResult.getResponse().getContentAsString(), Map.class);
        Long userId = Long.valueOf(userResp.get("id").toString());

        // 2. Create Product (with valid categoryId)
        Map<String, Object> productDto = Map.of(
                "name", "Book",
                "price", 19.99,
                "stock", 10,
                "categoryId", categoryId
        );
        MvcResult productResult = mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andReturn();
        Map<?,?> productResp = objectMapper.readValue(productResult.getResponse().getContentAsString(), Map.class);
        Long productId = Long.valueOf(productResp.get("id").toString());

        // 3. Place Order
        Map<String, Object> orderItem = Map.of(
                "productId", productId,
                "quantity", 2
        );
        Map<String, Object> orderDto = Map.of(
                "userId", userId,
                "orderItems", Collections.singletonList(orderItem)
        );
        MvcResult orderResult = mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andReturn();
        Map<?,?> orderResp = objectMapper.readValue(orderResult.getResponse().getContentAsString(), Map.class);
        assertThat(orderResp.get("user")).isNotNull();
        assertThat(orderResp.get("orderItems")).isInstanceOfAny(java.util.List.class);
    }
}
