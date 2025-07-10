package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    // Removed unused customerRepository
    private final com.example.demo.repository.UserRepository userRepository;
    @Override
    @Transactional
    public Order processOrder(Order order) {
        order.setStatus(OrderStatus.PROCESSING);
        return orderRepository.save(order);
    }
    @Override
    @Transactional
    public Order createOrder(com.example.demo.dto.OrderDTO orderDTO) {
        // Find customer (or user)
        com.example.demo.entity.User user = null;
        if (orderDTO.getCustomerId() != null) {
            user = userRepository.findById(orderDTO.getCustomerId())
                    .orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("User not found with id: " + orderDTO.getCustomerId()));
        }

        Order order = Order.builder()
                .orderDate(java.time.LocalDateTime.now())
                .status(com.example.demo.entity.OrderStatus.PENDING)
                .user(user)
                .build();

        java.util.List<OrderItem> items = new java.util.ArrayList<>();
        for (com.example.demo.dto.OrderItemDTO itemDTO : orderDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Product not found with id: " + itemDTO.getProductId()));
            if (product.getStock() < itemDTO.getQuantity()) {
                throw new IllegalStateException("Not enough stock for product: " + product.getName());
            }
            product.setStock(product.getStock() - itemDTO.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemDTO.getQuantity())
                    .price(product.getPrice())
                    .build();
            items.add(orderItem);
        }
        order.setItems(items);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        // Find the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        // Only allow cancelling if order is PENDING
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be cancelled.");
        }
        // Restore product stock for each order item
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                Product product = item.getProduct();
                if (product != null) {
                    product.setStock(product.getStock() + item.getQuantity());
                    productRepository.save(product);
                }
            }
        }
        // Update order status
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order placeOrder(Order order) {
        // For each item, check product stock and reduce it
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                Product product = productRepository.findById(item.getProduct().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + item.getProduct().getId()));
                if (product.getStock() < item.getQuantity()) {
                    throw new IllegalStateException("Not enough stock for product: " + product.getName());
                }
                product.setStock(product.getStock() - item.getQuantity());
                productRepository.save(product);
                item.setProduct(product); // ensure managed entity
                item.setOrder(order);
            }
        }
        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }
}
