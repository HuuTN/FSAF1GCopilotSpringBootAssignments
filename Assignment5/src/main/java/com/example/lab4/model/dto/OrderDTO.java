package com.example.lab4.model.dto;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {
    private Long id;
    private Long userId;
    private List<Long> orderItemIds = new ArrayList<>();
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<Long> getOrderItemIds() { return orderItemIds; }
    public void setOrderItemIds(List<Long> orderItemIds) { this.orderItemIds = orderItemIds; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
