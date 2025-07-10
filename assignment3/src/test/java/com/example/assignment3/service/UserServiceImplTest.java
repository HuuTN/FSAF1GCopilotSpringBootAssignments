package com.example.assignment3.service;

import com.example.assignment3.dto.UserDTO;
import com.example.assignment3.model.User;
import com.example.assignment3.repository.UserRepository;
import com.example.assignment3.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John");
        userDTO.setEmail("john@example.com");
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO created = userService.createUser(userDTO);
        assertNotNull(created.getId());
        assertEquals("John", created.getName());
        assertEquals("john@example.com", created.getEmail());
    }
}
