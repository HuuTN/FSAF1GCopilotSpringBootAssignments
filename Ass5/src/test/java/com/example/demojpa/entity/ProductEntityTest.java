package com.example.demojpa.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {
    @Test
    void testGetterSetter() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("prod1");
        p1.setDescription("desc");
        p1.setPrice(10.0);
        p1.setStockQuantity(5);
        Product p2 = new Product();
        p2.setId(1L);
        p2.setName("prod1");
        p2.setDescription("desc");
        p2.setPrice(10.0);
        p2.setStockQuantity(5);
        assertEquals(p1.getId(), p2.getId());
        assertEquals(p1.getName(), p2.getName());
        assertEquals(p1.getDescription(), p2.getDescription());
        assertEquals(p1.getPrice(), p2.getPrice());
        assertEquals(p1.getStockQuantity(), p2.getStockQuantity());
        p2.setId(2L);
        assertNotEquals(p1.getId(), p2.getId());
    }
}
