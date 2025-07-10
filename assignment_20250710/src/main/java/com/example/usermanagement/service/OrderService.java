package com.example.usermanagement.service;

import com.example.usermanagement.constant.OrderStatus;
import com.example.usermanagement.entity.Order;

public interface OrderService {
    Order createOrder(Long userId, Long productId, int quantity);
    void cancelOrder(Long orderId);
    void updateOrderStatus(Long orderId, OrderStatus status);
}