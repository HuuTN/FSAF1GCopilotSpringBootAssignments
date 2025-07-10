package com.example.demojpa.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ReviewDTOTest {
    @Test
    void testGetterSetterEqualsHashCode() {
        ReviewDTO r1 = new ReviewDTO();
        r1.setId(1L);
        ReviewDTO r2 = new ReviewDTO();
        r2.setId(1L);
        assertThat(r1).isEqualTo(r2);
        assertThat(r1).hasSameHashCodeAs(r2);
        assertThat(r1).isNotEqualTo(null);
        assertThat(r1).isNotEqualTo("string");
        assertThat(r1).isEqualTo(r1);
    }
}
