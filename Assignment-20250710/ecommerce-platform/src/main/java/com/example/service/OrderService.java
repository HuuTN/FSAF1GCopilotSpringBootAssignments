package com.example.service;

import com.example.entity.Order;
import com.example.dto.OrderItemDTO;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    List<Order> getOrdersByUserName(String userName);
    List<Order> getOrdersByStatus(String status);
    void cancelOrder(Long orderId);
    Order placeOrder(Long userId, Long customerId, List<OrderItemDTO> orderItems);
}
