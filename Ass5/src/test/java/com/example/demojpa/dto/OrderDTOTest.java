package com.example.demojpa.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class OrderDTOTest {
    @Test
    void testGetterSetterEqualsHashCode() {
        OrderDTO o1 = new OrderDTO();
        o1.setId(1L);
        OrderDTO o2 = new OrderDTO();
        o2.setId(1L);
        assertThat(o1).isEqualTo(o2);
        assertThat(o1).hasSameHashCodeAs(o2);
        assertThat(o1).isNotEqualTo(null);
        assertThat(o1).isNotEqualTo("string");
        assertThat(o1).isEqualTo(o1);
    }
}
