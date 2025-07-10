// add anotation @WebMvcTest(UserController.class)
package com.example.usermanagement.controller;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;

import com.example.usermanagement.service.UserService;
import com.example.usermanagement.dto.UserDTO;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUserById() throws Exception {
        // Arrange
        Long userId = 1L;
        UserDTO mockUser = new UserDTO();
        mockUser.setId(userId);
        mockUser.setName("John Doe");
        mockUser.setEmail("john.doe@example.com");

        when(userService.getUserById(userId)).thenReturn(mockUser);

        // Act & Assert
        mockMvc.perform(get("/api/v1/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(userService, times(1)).getUserById(userId);
    }
}
