package com.example.lab3.exception;

// Custom exception for not found resources
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
