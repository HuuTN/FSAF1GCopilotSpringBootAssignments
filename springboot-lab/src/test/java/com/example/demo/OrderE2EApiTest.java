package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.entity.Product;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jayway.jsonpath.JsonPath;
import java.time.LocalDateTime;

@AutoConfigureMockMvc
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderE2EApiTest {
        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private MockMvc mockMvc;

        private String apiUrl(String path) {
                return "http://localhost:" + port + "/api" + path;
        }

        /**
         * Full integration test: verifies the workflow from user creation to placing an
         * order and adding an order item.
         * This test uses the real application context and database.
         */
        @Test
        @DisplayName("Full Integration: user, product, order, order item workflow")
        void fullIntegrationUserOrderWorkflow() throws Exception {
                // 1. Create user
                User user = new User();
                user.setUsername("integrationuser");
                user.setEmail("integration@example.com");
                ResponseEntity<User> userResp = restTemplate.postForEntity(apiUrl("/users"), user, User.class);
                Assertions.assertEquals(HttpStatus.OK, userResp.getStatusCode());
                User createdUser = userResp.getBody();
                Assertions.assertNotNull(createdUser);
                Assertions.assertNotNull(createdUser.getId());
                Assertions.assertEquals("integrationuser", createdUser.getUsername());

                // 2. Create product
                Product product = new Product();
                product.setName("Integration Product");
                product.setPrice(99.99);
                ResponseEntity<Product> prodResp = restTemplate.postForEntity(apiUrl("/products"), product,
                                Product.class);
                Assertions.assertEquals(HttpStatus.OK, prodResp.getStatusCode());
                Product createdProduct = prodResp.getBody();
                Assertions.assertNotNull(createdProduct);
                Assertions.assertNotNull(createdProduct.getId());
                Assertions.assertEquals("Integration Product", createdProduct.getName());

                // 3. Place order
                Order order = new Order();
                order.setUser(createdUser);
                order.setStatus("NEW");
                ResponseEntity<Order> orderResp = restTemplate.postForEntity(apiUrl("/orders"), order, Order.class);
                Assertions.assertEquals(HttpStatus.OK, orderResp.getStatusCode());
                Order createdOrder = orderResp.getBody();
                Assertions.assertNotNull(createdOrder);
                Assertions.assertNotNull(createdOrder.getId());
                Assertions.assertEquals("NEW", createdOrder.getStatus());
                // // ...existing code...

                // 4. Add order item
                OrderItem item = new OrderItem();
                item.setOrder(createdOrder);
                item.setProduct(createdProduct);
                item.setQuantity(2);
                ResponseEntity<OrderItem> itemResp = restTemplate.postForEntity(apiUrl("/order-items"), item,
                                OrderItem.class);
                Assertions.assertEquals(HttpStatus.OK, itemResp.getStatusCode());
                OrderItem createdItem = itemResp.getBody();
                Assertions.assertNotNull(createdItem);
                Assertions.assertNotNull(createdItem.getId());
                Assertions.assertEquals(2, createdItem.getQuantity());
                // // ...existing code...

                // 5. Fetch and verify order
                ResponseEntity<Order[]> userOrdersResp = restTemplate
                                .getForEntity(apiUrl("/orders/user/" + createdUser.getId()), Order[].class);
                Assertions.assertEquals(HttpStatus.OK, userOrdersResp.getStatusCode());
                Order[] userOrders = userOrdersResp.getBody();
                System.out.println("Fetched orders for user: " + createdUser.getId() + " -> "
                                + (userOrders == null ? "null" : userOrders.length));
                // Log the full contents of userOrders as JSON
                if (userOrders != null) {
                        try {
                                String userOrdersJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userOrders);
                                System.out.println("userOrders JSON: " + userOrdersJson);
                        } catch (Exception e) {
                                System.out.println("Failed to serialize userOrders: " + e.getMessage());
                        }
                        for (Order o : userOrders) {
                                System.out.println("Order: id=" + o.getId() + ", status=" + o.getStatus() + ", userId="
                                                + (o.getUser() != null ? o.getUser().getId() : null));
                        }
                }
                Assertions.assertNotNull(userOrders);
                Assertions.assertTrue(userOrders.length > 0, "No orders found for user");
                Order fetchedOrder = userOrders[0];
                System.out.println("Fetched order status: " + fetchedOrder.getStatus());
                Assertions.assertEquals("NEW", fetchedOrder.getStatus());
                Assertions.assertEquals(createdUser.getId(), fetchedOrder.getUser().getId());
        }

        @Test
        void fullWorkflow_withMockMvc() throws Exception {
                // 1. Create user
                String userJson = "{" +
                                "\"username\":\"mockuser\"," +
                                "\"email\":\"mockuser@example.com\"}";
                String userResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn().getResponse().getContentAsString();
                                System.out.println(userResponse);
                Number userIdNum = JsonPath.read(userResponse, "$.id");
                Long userId = userIdNum.longValue();

                // 2. Create product
                String productJson = "{" +
                                "\"name\":\"Mock Product\"," +
                                "\"price\":123.45}";
                String productResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(productJson))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn().getResponse().getContentAsString();
                Number productIdNum = JsonPath.read(productResponse, "$.id");
                Long productId = productIdNum.longValue();

                // 3. Create order
                String orderJson = String.format("{" +
                                "\"user\": {\"id\": %d}," +
                                "\"status\": \"NEW\"," +
                                "\"orderDate\": \"%s\"}", userId, LocalDateTime.now().toString());
                mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(orderJson))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }
}
