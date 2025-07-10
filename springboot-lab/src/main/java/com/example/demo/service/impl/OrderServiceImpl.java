package com.example.demo.service.impl;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> findByOrderDateAfter(LocalDateTime date) {
        return orderRepository.findByOrderDateAfter(date);
    }

    @Override
    public List<Order> findOrdersWithMinItems(int minItems) {
        return orderRepository.findOrdersWithMinItems(minItems);
    }

    // TDD: Implement cancelOrder
    @Override
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus("CANCELLED");
        return orderRepository.save(order);
    }
}
