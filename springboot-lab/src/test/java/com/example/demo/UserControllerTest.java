package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = com.example.demo.controller.ApiController.class)
class UserControllerTest {
    @MockBean
    private com.example.demo.service.UserService userService;
    @MockBean
    private com.example.demo.service.ProductService productService;
    @MockBean
    private com.example.demo.service.CategoryService categoryService;
    @MockBean
    private com.example.demo.service.OrderService orderService;
    @MockBean
    private com.example.demo.service.ReviewService reviewService;
    @MockBean
    private com.example.demo.service.OrderItemService orderItemService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/users should return all users")
    void getAllUsers_shouldReturnUserList() throws Exception {
        User user1 = new User(); user1.setId(1L); user1.setUsername("alice");
        User user2 = new User(); user2.setId(2L); user2.setUsername("bob");
        List<User> users = Arrays.asList(user1, user2);
        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("alice")))
                .andExpect(jsonPath("$[1].username", is("bob")));
    }

    @Test
    @DisplayName("GET /api/users/{id} should return user by id")
    void getUser_shouldReturnUserById() throws Exception {
        User user = new User(); user.setId(1L); user.setUsername("alice");
        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("alice")));
    }
}
