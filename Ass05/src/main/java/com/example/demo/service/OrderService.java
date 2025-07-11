package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import java.util.List;

public interface OrderService {
    void cancelOrder(Long orderId);
    Order placeOrder(Long userId, List<OrderItem> items);
}
