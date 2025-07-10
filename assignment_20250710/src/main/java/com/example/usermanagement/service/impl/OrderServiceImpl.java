package com.example.usermanagement.service.impl;

import com.example.usermanagement.constant.OrderStatus;
import com.example.usermanagement.entity.Order;
import com.example.usermanagement.entity.OrderItem;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.exception.EntityNotFoundException;
import com.example.usermanagement.repository.OrderItemRepository;
import com.example.usermanagement.repository.OrderRepository;
import com.example.usermanagement.repository.ProductRepository;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        if (product.getStock() < quantity) {
            throw new EntityNotFoundException("Not enough product in stock");
        }
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        OrderItem item = OrderItem.builder()
                .product(product)
                .quantity(quantity)
                .price(product.getPrice())
                .build();
        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.CREATED)
                .orderItems(List.of(item))
                .build();
        item.setOrder(order);
        orderRepository.save(order);
        orderItemRepository.save(item);
        return order;
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Only PENDING orders can be cancelled");
        }
        order.setStatus(OrderStatus.CANCELLED);
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }
        orderRepository.save(order);
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }
} 