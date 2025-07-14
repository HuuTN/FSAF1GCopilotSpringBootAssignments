package com.example.lab4.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Optional;

import com.example.lab4.model.dto.UserDTO;
import com.example.lab4.service.serviceimpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenGetUserById_thenReturnUserDTO() throws Exception {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("John Doe");
        when(userService.getUserById(1L)).thenReturn(Optional.of(userDTO));

        // Act & Assert
        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("John Doe"));
    }

    @Test
    void whenGetAllUsers_thenReturnPage() throws Exception {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("John Doe");
        userDTO.setEmail("john@example.com");
        org.springframework.data.domain.Page<UserDTO> page = new org.springframework.data.domain.PageImpl<>(java.util.List.of(userDTO));
        when(userService.getAllUsers(org.springframework.data.domain.PageRequest.of(0, 10)))
            .thenReturn(page);
    }

    @Test
    void whenCreateUser_thenReturnCreated() throws Exception {
        // Arrange
        UserDTO dto = new UserDTO();
        dto.setUsername("newuser");
        dto.setEmail("a@a.com");
        UserDTO saved = new UserDTO();
        saved.setId(10L);
        saved.setUsername("newuser");
        saved.setEmail("a@a.com");
        when(userService.createUser(org.mockito.ArgumentMatchers.any(UserDTO.class))).thenReturn(saved);

        // Act & Assert
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    void whenUpdateUser_found() throws Exception {
        // Arrange
        UserDTO dto = new UserDTO();
        dto.setUsername("update");
        dto.setEmail("b@b.com");
        UserDTO updated = new UserDTO();
        updated.setId(1L);
        updated.setUsername("update");
        updated.setEmail("b@b.com");
        when(userService.updateUser(org.mockito.ArgumentMatchers.eq(1L), org.mockito.ArgumentMatchers.any(UserDTO.class)))
            .thenReturn(Optional.of(updated));

        // Act & Assert
        mockMvc.perform(put("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void whenUpdateUser_notFound() throws Exception {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setId(2L);
        userDTO.setUsername("NotFound");
        userDTO.setEmail("notfound@example.com");
        when(userService.updateUser(eq(2L), any(UserDTO.class))).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(put("/api/v1/users/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeleteUser_found() throws Exception {
        // Arrange
        when(userService.deleteUser(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDeleteUser_notFound() throws Exception {
        // Arrange
        when(userService.deleteUser(2L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/users/2"))
                .andExpect(status().isNotFound());
    }
}
