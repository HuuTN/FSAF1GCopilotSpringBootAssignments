package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Map<String, Object> orderRequest) {
        Long userId = Long.valueOf(orderRequest.get("userId").toString());
        List<Map<String, Object>> items = (List<Map<String, Object>>) orderRequest.get("items");
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<OrderItem> orderItems = new ArrayList<>();
        for (Map<String, Object> item : items) {
            Long productId = Long.valueOf(item.get("productId").toString());
            Integer quantity = Integer.valueOf(item.get("quantity").toString());
            Product product = productRepository.findById(productId).orElse(null);
            if (product == null || product.getStock() < quantity) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            // Deduct stock only after order is successfully placed
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
            orderItems.add(orderItem);
        }
        // Deduct stock for each product
        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            product.setStock(product.getStock() - orderItem.getQuantity());
            productRepository.save(product);
        }
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setStatus("PENDING");
        Order saved = orderRepository.save(order);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
