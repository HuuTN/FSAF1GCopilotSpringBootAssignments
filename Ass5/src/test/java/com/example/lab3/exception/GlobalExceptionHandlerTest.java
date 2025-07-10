package com.example.lab3.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    @Test
    void testHandleNotFound() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResourceNotFoundException ex = new ResourceNotFoundException("Not found");
        ResponseEntity<String> response = handler.handleNotFound(ex);
        assertEquals(404, response.getStatusCode().value());
        assertEquals("Not found", response.getBody());
    }
}
