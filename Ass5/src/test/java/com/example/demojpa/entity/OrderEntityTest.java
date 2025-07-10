package com.example.demojpa.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class OrderEntityTest {
    @Test
    void testEqualsAndHashCode() {
        Order o1 = new Order();
        o1.setId(1L);
        Order o2 = new Order();
        o2.setId(1L);
        Order o3 = new Order();
        o3.setId(2L);
        assertThat(o1).isEqualTo(o2);
        assertThat(o1).hasSameHashCodeAs(o2);
        assertThat(o1).isNotEqualTo(o3);
        assertThat(o1).isNotEqualTo(null);
        assertThat(o1).isNotEqualTo("string");
        assertThat(o1).isEqualTo(o1);
    }

    @Test
    void testToString() {
        Order o = new Order();
        o.setId(1L);
        assertThat(o.toString()).contains("Order");
    }
}
