package com.example.lab4.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.domain.Pageable;

import com.example.lab4.dto.UserDTO;
import com.example.lab4.entity.User;
import com.example.lab4.repository.UserRepository;

import java.util.List;
import java.util.Optional;

class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserServiceNotNull() {
        assertNotNull(userService);
    }

    @Test
    void testGetAllUsers() {
        Pageable pageable = org.mockito.Mockito.mock(Pageable.class);
        List<User> users = List.of(new User());
        org.mockito.Mockito.when(userRepository.findAll(pageable))
            .thenReturn(new org.springframework.data.domain.PageImpl<>(users));
        var result = userService.getAllUsers(pageable);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetUserById_found() {
        User user = new User();
        user.setId(1L);
        org.mockito.Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        var result = userService.getUserById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testGetUserById_notFound() {
        org.mockito.Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());
        var result = userService.getUserById(2L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateUser() {
        UserDTO dto = new UserDTO();
        dto.setUsername("u");
        dto.setEmail("e@e.com");
        User user = new User();
        user.setUsername("u");
        user.setEmail("e@e.com");
        User saved = new User();
        saved.setId(10L);
        saved.setUsername("u");
        saved.setEmail("e@e.com");
        org.mockito.Mockito.when(userRepository.save(org.mockito.Mockito.any(User.class))).thenReturn(saved);
        var result = userService.createUser(dto);
        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    @Test
    void testUpdateUser_found() {
        UserDTO dto = new UserDTO();
        dto.setUsername("u");
        dto.setEmail("e@e.com");
        org.mockito.Mockito.when(userRepository.existsById(1L)).thenReturn(true);
        User saved = new User();
        saved.setId(1L);
        saved.setUsername("u");
        saved.setEmail("e@e.com");
        org.mockito.Mockito.when(userRepository.save(org.mockito.Mockito.any(User.class))).thenReturn(saved);
        var result = userService.updateUser(1L, dto);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testUpdateUser_notFound() {
        UserDTO dto = new UserDTO();
        org.mockito.Mockito.when(userRepository.existsById(2L)).thenReturn(false);
        var result = userService.updateUser(2L, dto);
        assertTrue(result.isEmpty());
    }

    @Test
    void testDeleteUser_found() {
        org.mockito.Mockito.when(userRepository.existsById(1L)).thenReturn(true);
        org.mockito.Mockito.doNothing().when(userRepository).deleteById(1L);
        assertTrue(userService.deleteUser(1L));
    }

    @Test
    void testDeleteUser_notFound() {
        org.mockito.Mockito.when(userRepository.existsById(2L)).thenReturn(false);
        assertFalse(userService.deleteUser(2L));
    }
}
