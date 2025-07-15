package com.example.demojpa.service;

import com.example.demojpa.entity.User;
import com.example.demojpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private com.example.demojpa.service.impl.UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById_found() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> result = userService.getUserById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testGetUserById_notFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<User> result = userService.getUserById(2L);
        assertFalse(result.isPresent());
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUsername("test");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User created = userService.createUser(user);
        assertEquals("test", created.getUsername());
    }

    @Test
    void testUpdateUser_found() {
        User user = new User();
        user.setId(1L);
        user.setUsername("updated");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.updateUser(1L, user);
        assertNotNull(result);
        assertEquals("updated", result.getUsername());
    }

    @Test
    void testUpdateUser_notFound() {
        User user = new User();
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.updateUser(2L, user));
    }

    @Test
    void testDeleteUser_found() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(1L);
        assertDoesNotThrow(() -> userService.deleteUser(1L));
    }

    @Test
    void testDeleteUser_notFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.deleteUser(2L));
    }
}
