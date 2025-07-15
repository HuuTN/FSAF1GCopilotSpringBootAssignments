package com.example.demo.service;

import com.example.demo.dto.CreateOrderRequestDTO;
import com.example.demo.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    OrderDTO placeOrder(CreateOrderRequestDTO request);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrdersByUser(Long userId);
}
