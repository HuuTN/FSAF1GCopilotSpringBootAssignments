package com.example.demojpa.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {
    @Test
    void testGetterSetter() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setPassword("pass");
        User user2 = new User();
        user2.setId(1L);
        user2.setUsername("user1");
        user2.setEmail("user1@example.com");
        user2.setPassword("pass");
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getUsername(), user2.getUsername());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getPassword(), user2.getPassword());
        user2.setId(2L);
        assertNotEquals(user1.getId(), user2.getId());
    }
}
