package com.example.demo.service;

import com.example.demo.model.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
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
    void createUser_shouldReturnSavedUserDto() {
        UserDto userDto = new UserDto(11L, "Nguyen", "Nguyen@example.com");
        User savedUser = new User(11L, "Nguyen", "Nguyen@example.com");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDto result = userService.createUser(userDto);

        assertNotNull(result);
        assertEquals(11L, result.getId());
        assertEquals("Nguyen", result.getName());
        assertEquals("Nguyen@example.com", result.getEmail());
    }
}
