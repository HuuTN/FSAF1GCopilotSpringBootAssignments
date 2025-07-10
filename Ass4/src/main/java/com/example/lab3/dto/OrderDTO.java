package com.example.lab3.dto;

public class OrderDTO {
    private Long id;
    private Long userId;
    // Thêm các trường khác nếu cần

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
