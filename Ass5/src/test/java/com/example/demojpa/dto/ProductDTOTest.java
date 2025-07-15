package com.example.demojpa.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {
    @Test
    void testGetterSetter() {
        ProductDTO dto1 = new ProductDTO();
        dto1.setId(1L);
        dto1.setName("prod1");
        dto1.setDescription("desc");
        dto1.setPrice(10.0);
        ProductDTO dto2 = new ProductDTO();
        dto2.setId(1L);
        dto2.setName("prod1");
        dto2.setDescription("desc");
        dto2.setPrice(10.0);
        assertEquals(dto1.getId(), dto2.getId());
        assertEquals(dto1.getName(), dto2.getName());
        assertEquals(dto1.getDescription(), dto2.getDescription());
        assertEquals(dto1.getPrice(), dto2.getPrice());
        dto2.setId(2L);
        assertNotEquals(dto1.getId(), dto2.getId());
    }
}
