package com.example.usermanagement.dto;

import lombok.Data;

@Data
public class OrderPostRequest {
    private Long userId;
    private Long productId;
    private int quantity;
} 