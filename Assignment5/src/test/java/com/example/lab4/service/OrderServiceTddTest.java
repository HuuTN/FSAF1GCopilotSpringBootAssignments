package com.example.lab4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.lab4.model.entity.*;
import com.example.lab4.model.enums.OrderStatus;
import com.example.lab4.repository.OrderRepository;
import com.example.lab4.repository.ProductRepository;
import com.example.lab4.repository.UserRepository;
import com.example.lab4.service.serviceimpl.OrderServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

@SpringBootTest
@Transactional
class OrderServiceTddTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private Product product;
    private Order testOrder;
    private OrderItem orderItem;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password");
        userRepository.save(testUser);

        product = new Product();
        product.setName("Test Product");
        product.setStockQuantity(10);
        productRepository.save(product);

        testOrder = new Order();
        testOrder.setUser(testUser);
        testOrder.setStatus(OrderStatus.PENDING);
        testOrder.setTotalAmount(new BigDecimal("199.98"));
        orderRepository.save(testOrder);

        orderItem = new OrderItem();
        orderItem.setOrder(testOrder);
        orderItem.setProduct(product);
        orderItem.setQuantity(2);
        testOrder.getOrderItems().add(orderItem);
        product.setStockQuantity(product.getStockQuantity() - orderItem.getQuantity());
        productRepository.save(product);
        orderRepository.save(testOrder);
    }

    @Test
    void whenCancelPendingOrder_thenStatusIsCancelledAndStockIsRestored() {
        // Arrange
        Long orderId = testOrder.getId();
        int initialStock = product.getStockQuantity();

        // Act
        orderService.cancelOrder(orderId);

        // Assert
        Order cancelledOrder = orderRepository.findById(orderId).get();
        Product productAfterCancellation = productRepository.findById(product.getId()).get();

        assertEquals(OrderStatus.CANCELLED, cancelledOrder.getStatus());
        assertEquals(initialStock + orderItem.getQuantity(), productAfterCancellation.getStockQuantity());
    }
}
