package com.example.demo.workflow;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Rollback changes after each test
public class ECommerceWorkflowTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private OrderRepository orderRepository;

    @Test
    void testPlaceOrderWorkflow() throws Exception {
        // 1. Create a user via API call
        Map<String, Object> user = Map.of(
            "name", "Test User",
            "email", "testuser@example.com"
        );
        MvcResult userResult = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isCreated())
            .andReturn();
        Map<String, Object> userResponse = objectMapper.readValue(userResult.getResponse().getContentAsString(), Map.class);
        Long userId = Long.valueOf(userResponse.get("id").toString());

        // 2. Create a category via API call (or directly in DB if no endpoint)
        Map<String, Object> category = Map.of(
            "name", "Electronics"
        );
        MvcResult categoryResult = mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)))
            .andExpect(status().isCreated())
            .andReturn();
        Map<String, Object> categoryResponse = objectMapper.readValue(categoryResult.getResponse().getContentAsString(), Map.class);
        Long categoryId = Long.valueOf(categoryResponse.get("id").toString());

        // 3. Create a product via API call (with category)
        Map<String, Object> product = Map.of(
            "name", "Test Product",
            "price", 99.99,
            "stock", 10,
            "category", Map.of("id", categoryId)
        );
        MvcResult productResult = mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isCreated())
            .andReturn();
        Map<String, Object> productResponse = objectMapper.readValue(productResult.getResponse().getContentAsString(), Map.class);
        Long productId = Long.valueOf(productResponse.get("id").toString());

        // 4. Place an order
        Map<String, Object> orderRequest = Map.of(
            "user", Map.of("id", userId),
            "items", List.of(Map.of(
                "product", Map.of("id", productId),
                "quantity", 2
            ))
        );
        MvcResult orderResult = mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
            .andReturn();

        int status = orderResult.getResponse().getStatus();
        if (status != 201 && status != 200) {
            System.out.println("Order API response status: " + status);
            System.out.println("Order API response body: " + orderResult.getResponse().getContentAsString());
        }
        assertThat(status)
            .withFailMessage("Expected HTTP 201 or 200 but got %s. Body: %s", status, orderResult.getResponse().getContentAsString())
            .isIn(201, 200);

        // Assert: Verify the order was created in the database
        List<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getStatus()).isEqualTo("PENDING");
    }
}
