package com.example.lab4.entity.base;

import org.junit.jupiter.api.Test;

import com.example.lab4.model.entity.base.Auditable;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class AuditableTest {
    static class AuditableStub extends Auditable<String> {}

    @Test
    void testCreatedAtAndUpdatedAt() {
        AuditableStub auditable = new AuditableStub();
        LocalDateTime now = LocalDateTime.now();
        auditable.setCreatedAt(now);
        auditable.setUpdatedAt(now.plusHours(1));
        assertEquals(now, auditable.getCreatedAt());
        assertEquals(now.plusHours(1), auditable.getUpdatedAt());
    }
}
