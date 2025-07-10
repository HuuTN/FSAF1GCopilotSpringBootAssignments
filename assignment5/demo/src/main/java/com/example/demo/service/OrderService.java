package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;

public interface OrderService {
    void cancelOrder(Long orderId);
    Order placeOrder(Order order);
    Order createOrder(OrderDTO orderDTO);
    Order processOrder(Order order);
}
