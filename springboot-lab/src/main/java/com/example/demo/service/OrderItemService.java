package com.example.demo.service;

import com.example.demo.entity.OrderItem;
import java.util.List;

public interface OrderItemService {
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductId(Long productId);
}
