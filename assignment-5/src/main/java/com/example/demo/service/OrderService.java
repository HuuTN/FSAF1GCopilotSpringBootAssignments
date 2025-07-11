package com.example.demo.service;

import com.example.demo.entity.Order;

public interface OrderService {

    Order getOrderById(Long orderId);

    void cancelOrder(Long orderId);

    Order createdOrder(Order order);

    // Other methods related to order management
}