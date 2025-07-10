package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GloblaException {
     @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<String> handleHttpMessageNotWritable(HttpMessageNotWritableException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getMessage());
    }
}
