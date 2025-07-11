package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if (!"PENDING".equals(order.getStatus())) {
            throw new IllegalStateException("Only PENDING orders can be cancelled");
        }
        order.setStatus("CANCELLED");
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                Product product = item.getProduct();
                if (product != null) {
                    product.setStock(product.getStock() + item.getQuantity());
                    productRepository.save(product);
                }
            }
        }
        orderRepository.save(order);
    }

    @Override
    public Order placeOrder(Long userId, List<OrderItem> items) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        for (OrderItem item : items) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getId());
            }
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
            item.setProduct(product);
        }
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(items);
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }
}
