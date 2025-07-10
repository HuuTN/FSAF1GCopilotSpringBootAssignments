package com.example.demo.service;

import com.example.demo.entity.Order;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> findAll();
    List<Order> findByUserId(Long userId);
    List<Order> findByOrderDateAfter(LocalDateTime date);
    List<Order> findOrdersWithMinItems(int minItems);
    Order cancelOrder(Long orderId);
    Order save(Order order);
}
