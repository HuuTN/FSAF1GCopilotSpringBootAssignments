package com.example.lab3;

import com.example.lab3.dto.UserDTO;
import com.example.lab3.dto.ProductDTO;
import com.example.lab3.entity.Order;
import com.example.lab3.entity.OrderStatus;
import com.example.lab3.repository.OrderRepository;
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
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testPlaceOrderWorkflow() throws Exception {
        // Arrange: Tạo user
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("workflowuser");
        userDTO.setEmail("workflow@example.com");
        String userJson = objectMapper.writeValueAsString(userDTO);
        String userResponse = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        UserDTO createdUser = objectMapper.readValue(userResponse, UserDTO.class);

        // Arrange: Tạo product
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Workflow Product");
        productDTO.setPrice(100.0);
        productDTO.setStock(10); // Sửa lại đúng setter
        String productJson = objectMapper.writeValueAsString(productDTO);
        String productResponse = mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        ProductDTO createdProduct = objectMapper.readValue(productResponse, ProductDTO.class);

        // Act: Đặt hàng
        String orderRequest = "{" +
                "\"userId\":" + createdUser.getId() + "," +
                "\"items\":[{" +
                "\"productId\":" + createdProduct.getId() + "," +
                "\"quantity\":2}]" +
                "}";
        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderRequest))
                .andExpect(status().isCreated());

        // Assert: Kiểm tra đơn hàng trong DB
        List<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getStatus()).isEqualTo(OrderStatus.PENDING);
    }
}
