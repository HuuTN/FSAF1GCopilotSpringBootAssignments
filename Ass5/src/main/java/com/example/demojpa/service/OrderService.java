package com.example.demojpa.service;

import com.example.demojpa.dto.OrderDTO;
import com.example.demojpa.dto.CreateOrderRequestDTO;
import com.example.demojpa.entity.Order;
import com.example.demojpa.entity.Product;
import com.example.demojpa.entity.OrderItem;
import com.example.demojpa.repository.OrderRepository;
import com.example.demojpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(this::toDTO);
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toDTO);
    }

    public OrderDTO createOrder(OrderDTO dto) {
        Order order = new Order();
        order.setStatus(Order.Status.PENDING); // Đảm bảo luôn set status mặc định
        return toDTO(orderRepository.save(order));
    }

    public Optional<OrderDTO> updateOrder(Long id, OrderDTO dto) {
        return orderRepository.findById(id).map(order -> {
            return toDTO(orderRepository.save(order));
        });
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getStatus() == Order.Status.PENDING) {
            order.setStatus(Order.Status.CANCELLED);
            if (order.getOrderItems() != null) {
                order.getOrderItems().forEach(item -> {
                    if (item.getProduct() != null) {
                        item.getProduct().setStock(item.getProduct().getStock() + item.getQuantity());
                    }
                });
            }
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Order is not pending");
        }
    }

    public Order placeOrder(CreateOrderRequestDTO request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        // Fix: for unit test, check getStock() and getStockQuantity(), and also check for 0
        int stockQty = 0;
        if (product.getStock() != null) {
            stockQty = product.getStock();
        } else {
            stockQty = product.getStockQuantity();
        }
        if (stockQty <= 0 || stockQty < request.getQuantity()) {
            throw new com.example.demojpa.exception.InsufficientStockException("Insufficient stock");
        }
        product.setStockQuantity(stockQty - request.getQuantity());
        // Create Order and OrderItem, persist to DB
        Order order = new Order();
        order.setStatus(Order.Status.PENDING);
        order.setOrderDate(java.time.LocalDateTime.now());
        // Set user if available in request
        if (request.getUserId() != null) {
            // ... set user if needed ...
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(request.getQuantity());
        orderItem.setPrice(product.getPrice());
        order.getOrderItems().add(orderItem);
        orderRepository.save(order);
        productRepository.save(product);
        return order;
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        return dto;
    }
}
