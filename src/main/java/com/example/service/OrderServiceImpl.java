package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public void cancelOrder(Long orderId) {
        // TODO: Implement logic to find order, update status, and restore product stock
    }
}
