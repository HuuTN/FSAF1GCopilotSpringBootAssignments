package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ApiControllerIntegrationTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private UserRepository userRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    private User testUser;
    private Product testProduct;
    private Order testOrder;

    @BeforeEach
    void setup() {
        // Setup test data
        testUser = new User();
        testUser.setUsername("testuser");
        userRepository.save(testUser);

        testProduct = new Product();
        testProduct.setName("Test Product");
        testProduct.setPrice(100.0);
        productRepository.save(testProduct);

        testOrder = new Order();
        testOrder.setUser(testUser);
        orderRepository.save(testOrder);
    }

    // User APIs
    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(get("/api/users/" + testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testUser.getId()));
    }

    @Test
    void testSearchUserByUsername() throws Exception {
        // Assuming you have an endpoint for searching by username
        // mockMvc.perform(get("/api/users/search").param("username", "testuser"))
        //         .andExpect(status().isOk())
        //         .andExpect(jsonPath("$[0].username").value("testuser"));
    }

    // Order APIs
    @Test
    void testGetAllOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetOrdersByUserId() throws Exception {
        mockMvc.perform(get("/api/orders/user/" + testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testProcessOrder() throws Exception {
        mockMvc.perform(post("/api/orders/" + testOrder.getId() + "/process"))
                .andExpect(status().isOk());
    }

    // Product APIs
    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testSearchProductsByKeyword() throws Exception {
        mockMvc.perform(get("/api/products/search").param("keyword", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
