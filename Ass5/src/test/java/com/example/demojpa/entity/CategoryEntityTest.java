package com.example.demojpa.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class CategoryEntityTest {
    @Test
    void testEqualsAndHashCode() {
        Category c1 = new Category();
        c1.setId(1L);
        Category c2 = new Category();
        c2.setId(1L);
        Category c3 = new Category();
        c3.setId(2L);
        assertThat(c1).isEqualTo(c2);
        assertThat(c1).hasSameHashCodeAs(c2);
        assertThat(c1).isNotEqualTo(c3);
        assertThat(c1).isNotEqualTo(null);
        assertThat(c1).isNotEqualTo("string");
        assertThat(c1).isEqualTo(c1);
    }

    @Test
    void testToString() {
        Category c = new Category();
        c.setId(1L);
        assertThat(c.toString()).contains("Category");
    }
}
