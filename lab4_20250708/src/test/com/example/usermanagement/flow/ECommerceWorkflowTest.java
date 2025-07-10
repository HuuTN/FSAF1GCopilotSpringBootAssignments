// add @SpringBootTest and @AutoConfigureMockMvc, @Transactional
package com.example.usermanagement.flow;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ECommerceWorkflowTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPlaceOrderWorkflow() throws Exception {
        // Step 1: Create a user
        String userJson = "{"name":"John Doe","email":"john.doe@example.com"}";
        MvcResult userResult = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andReturn();

        String userId = extractIdFromResponse(userResult);

        // Step 2: Create a product
        String productJson = "{"name":"Laptop","price":1200.00,"category":"Electronics"}";
        MvcResult productResult = mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated())
                .andReturn();

        String productId = extractIdFromResponse(productResult);

        // Step 3: Place an order
        String orderJson = "{"userId":"" + userId + "","items":[{"productId":"" + productId + "","quantity":1}]}";
        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson))
                .andExpect(status().isCreated());
    }

    private String extractIdFromResponse(MvcResult result) throws Exception {
        // Extract ID from JSON response
        String responseContent = result.getResponse().getContentAsString();
        // Assuming response contains an "id" field
        return responseContent.substring(responseContent.indexOf("id":"") + 5, responseContent.indexOf(","));
    }
}
