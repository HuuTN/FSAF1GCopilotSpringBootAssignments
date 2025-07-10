package com.example.demojpa.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ReviewEntityTest {
    @Test
    void testEqualsAndHashCode() {
        Review r1 = new Review();
        r1.setId(1L);
        Review r2 = new Review();
        r2.setId(1L);
        Review r3 = new Review();
        r3.setId(2L);
        assertThat(r1).isEqualTo(r2);
        assertThat(r1).hasSameHashCodeAs(r2);
        assertThat(r1).isNotEqualTo(r3);
        assertThat(r1).isNotEqualTo(null);
        assertThat(r1).isNotEqualTo("string");
        assertThat(r1).isEqualTo(r1);
    }

    @Test
    void testToString() {
        Review r = new Review();
        r.setId(1L);
        assertThat(r.toString()).contains("Review");
    }
}
