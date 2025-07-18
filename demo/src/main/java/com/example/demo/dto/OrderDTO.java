package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private Long userId;
    private Set<OrderItemDTO> items;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Set<OrderItemDTO> getItems() { return items; }
    public void setItems(Set<OrderItemDTO> items) { this.items = items; }
}
