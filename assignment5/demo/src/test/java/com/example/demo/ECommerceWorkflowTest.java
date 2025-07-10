
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ECommerceWorkflowTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPlaceOrderEndToEnd() throws Exception {
        // 1. Create a user
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        String userJson = "{" +
                "\"name\":\"TestUser\"," +
                "\"email\":\"" + uniqueEmail + "\"," +
                "\"password\":\"password123\"}";
        String userResponse = null;
        try {
            userResponse = mockMvc.perform(
                    org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/users")
                            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                            .content(userJson)
            ).andExpect(status().isCreated())
             .andExpect(jsonPath("$.id").exists())
             .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            System.out.println("User creation failed: " + e.getMessage());
            throw e;
        }
        Long userId = com.fasterxml.jackson.databind.JsonNode.class.cast(
            new com.fasterxml.jackson.databind.ObjectMapper().readTree(userResponse)
        ).get("id").asLong();

        // 2. Create a product
        String productJson = "{" +
                "\"name\":\"TestProduct\"," +
                "\"description\":\"A product for testing\"," +
                "\"price\":99.99," +
                "\"stock\":10," +
                "\"categoryId\":1}";
        String productResponse = mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/products")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(productJson)
        ).andExpect(status().isCreated())
         .andExpect(jsonPath("$.id").exists())
         .andReturn().getResponse().getContentAsString();
        Long productId = com.fasterxml.jackson.databind.JsonNode.class.cast(
            new com.fasterxml.jackson.databind.ObjectMapper().readTree(productResponse)
        ).get("id").asLong();

        // 3. Place an order
        String orderJson = "{" +
                "\"customerId\":" + userId + "," +
                "\"items\":[{" +
                "\"productId\":" + productId + "," +
                "\"quantity\":2" +
                "}]" +
                "}";
        mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/orders")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(orderJson)
        ).andExpect(status().isCreated())
         .andExpect(jsonPath("$.id").exists())
         .andExpect(jsonPath("$.user.id").value(userId))
         .andExpect(jsonPath("$.items[0].product.id").value(productId))
         .andExpect(jsonPath("$.items[0].quantity").value(2));
    }
}
