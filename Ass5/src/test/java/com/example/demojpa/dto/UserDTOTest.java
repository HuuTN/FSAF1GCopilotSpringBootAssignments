package com.example.demojpa.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {
    @Test
    void testGetterSetter() {
        UserDTO dto1 = new UserDTO();
        dto1.setId(1L);
        dto1.setUsername("user1");
        dto1.setEmail("user1@example.com");
        UserDTO dto2 = new UserDTO();
        dto2.setId(1L);
        dto2.setUsername("user1");
        dto2.setEmail("user1@example.com");
        assertEquals(dto1.getId(), dto2.getId());
        assertEquals(dto1.getUsername(), dto2.getUsername());
        assertEquals(dto1.getEmail(), dto2.getEmail());
        dto2.setId(2L);
        assertNotEquals(dto1.getId(), dto2.getId());
    }
}
