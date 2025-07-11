package com.example.demo.service.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.exception.OrderCannotBeCanceledException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

    // Assuming you have an OrderRepository and ProductService injected here
     @Autowired
     private OrderRepository orderRepository;
    
    // @Autowired
     private ProductService productService;

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId)); // Assuming OrderNotFoundException is defined
    }

    @Override
    public void cancelOrder(Long orderId) {
        // Logic to cancel the order and update product stock
        Order order = getOrderById(orderId);
        if (order.getOrderItems().stream().allMatch(item -> item.getStatus() == OrderStatus.PENDING)) {
            order.getOrderItems().forEach(item -> item.setStatus(OrderStatus.CANCELED));
            orderRepository.save(order);
            order.getOrderItems().forEach(item -> productService.updateProductStock(item.getProduct().getId(), 1)); // Assuming updateProductStock method exists
        } else {
            throw new OrderCannotBeCanceledException(orderId); // Assuming OrderCannotBeCanceledException is defined
        }
       
    }

    @Override
    public Order createdOrder(Order order) {
        order.getOrderItems().forEach(item -> item.setStatus(OrderStatus.PENDING));
        return orderRepository.save(order);
    }
    
}
