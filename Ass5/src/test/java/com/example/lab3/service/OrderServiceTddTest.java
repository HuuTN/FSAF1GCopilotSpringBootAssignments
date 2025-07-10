package com.example.lab3.service;

import com.example.lab3.BaseIntegrationTest;
import com.example.lab3.entity.*;
import com.example.lab3.repository.OrderRepository;
import com.example.lab3.repository.ProductRepository;
import com.example.lab3.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTddTest extends BaseIntegrationTest {
    @Autowired
    private OrderService orderService;
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
    void setUp() {
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
