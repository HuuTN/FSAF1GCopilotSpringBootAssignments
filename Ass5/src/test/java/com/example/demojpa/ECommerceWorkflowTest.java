package com.example.demojpa;

import com.example.demojpa.dto.CreateOrderRequestDTO;
import com.example.demojpa.entity.Order;
import com.example.demojpa.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ECommerceWorkflowTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private OrderRepository orderRepository;

    @Test
    void testPlaceOrderWorkflow() throws Exception {
        // Arrange: Create a user
        String userJson = "{" +
                "\"username\":\"workflowuser\"," +
                "\"email\":\"workflowuser@example.com\"," +
                "\"password\":\"password\"}";
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated());

        // Arrange: Create a product
        String productJson = "{" +
                "\"name\":\"Workflow Product\"," +
                "\"description\":\"desc\"," +
                "\"price\":100.0," +
                "\"stock\":5}";
        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated());

        // Act: Place an order
        CreateOrderRequestDTO orderRequest = new CreateOrderRequestDTO();
        orderRequest.setProductId(1L);
        orderRequest.setQuantity(1);
        orderRequest.setUserId(1L);
        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isCreated());

        // Assert: Verify the order was created in the database
        List<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getStatus().name()).isEqualTo("PENDING");
    }
}
