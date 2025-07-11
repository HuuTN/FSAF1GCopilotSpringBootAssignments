package com.example.demo.exception;

public class OrderNotFoundException extends RuntimeException {
    private Long orderId;

    public OrderNotFoundException(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "OrderNotFoundException: Order with ID " + orderId + " not found.";
    }
}
