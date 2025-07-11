package com.example.demo.exception;

public class OrderCannotBeCanceledException extends RuntimeException {
    private Long orderId;

    public OrderCannotBeCanceledException(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "OrderCannotBeCanceledException: Order with ID " + orderId + " cannot be canceled.";
    }
}
