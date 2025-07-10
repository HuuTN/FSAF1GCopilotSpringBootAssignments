package com.example.demojpa.service;

import com.example.demojpa.entity.Order;
import com.example.demojpa.entity.Product;
import com.example.demojpa.entity.User;
import com.example.demojpa.entity.OrderItem;
import com.example.demojpa.repository.OrderRepository;
import com.example.demojpa.repository.ProductRepository;
import com.example.demojpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTddTest {
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private UserRepository userRepository;

    // TODO: setup test data (user, product, order, orderItem)
    private Order testOrder;
    private Product product;
    private int initialStock;
    private int orderQuantity;

    @BeforeEach
    void setUp() {
        // Create and persist a user
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        user = userRepository.save(user);

        // Create and persist a product
        product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setStock(10);
        product = productRepository.save(product);

        // Set initial stock and order quantity
        initialStock = product.getStock();
        orderQuantity = 1;

        // Create and persist an order
        testOrder = new Order();
        testOrder.setUser(user);
        testOrder.setStatus(Order.Status.PENDING);
        testOrder = orderRepository.save(testOrder);

        // Create and persist an order item
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(testOrder);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderQuantity);
        orderItem.setPrice(product.getPrice());
        // Add order item to order
        testOrder.getOrderItems().add(orderItem);
        orderRepository.save(testOrder);
        // Decrease product stock to simulate order placed
        product.setStock(product.getStock() - orderQuantity);
        productRepository.save(product);
    }

    @Test
    void whenCancelPendingOrder_thenStatusIsCancelledAndStockIsRestored() {
        Long orderId = testOrder.getId();
        int initialStock = product.getStock();
        int orderQuantity = 1; // giả định

        orderService.cancelOrder(orderId);

        Order cancelledOrder = orderRepository.findById(orderId).get();
        Product productAfterCancellation = productRepository.findById(product.getId()).get();

        assertEquals("CANCELLED", cancelledOrder.getStatus().name());
        assertEquals(initialStock + orderQuantity, productAfterCancellation.getStock());
    }
}
