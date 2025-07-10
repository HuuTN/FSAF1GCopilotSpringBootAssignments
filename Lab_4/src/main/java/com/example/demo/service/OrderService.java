package com.example.demo.service;

import com.example.demo.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
    Order createOrder(Order order);
    Optional<Order> updateOrder(Long id, Order orderDetails);
    boolean deleteOrder(Long id);

    List<Order> getOrdersByUserId(Long userId);
}
