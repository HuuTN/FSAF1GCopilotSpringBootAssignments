package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import com.example.demo.model.UserDto;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUserById_shouldReturnUser_whenUserExists() throws Exception {
        // Arrange: Use manual setters for UserDto
        UserDto userDto = new UserDto();
        userDto.setId(2L);
        userDto.setName("Jane Smith");
        userDto.setEmail("jane.smith@example.com");
        when(userService.getUserById(1L)).thenReturn(Optional.of(userDto));

        // Act & Assert: Use JSONPath and check for all fields
        mockMvc.perform(get("/api/v1/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Jane Smith"))
                .andExpect(jsonPath("$.email").value("jane.smith@example.com"));
    }

    @Test
    void getUserById_shouldReturnNotFound_whenUserDoesNotExist() throws Exception {
        // Arrange: Use a different ID and check for error structure
        when(userService.getUserById(99L)).thenReturn(Optional.empty());

        // Act & Assert: Check for error message and status
        mockMvc.perform(get("/api/v1/users/{id}", 99)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found"));
    }
}
