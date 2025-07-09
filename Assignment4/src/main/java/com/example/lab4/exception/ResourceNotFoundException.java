package com.example.lab4.exception;

// Custom exception for not found resources
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
