package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnitTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_shouldSaveOrderAndUpdateStock() {
        // Arrange
        User user = new User(1L, "Alice", "alice@example.com");
        Product product = new Product();
        product.setId(Long.valueOf(1));
        product.setName("Test Product");
        product.setStock(Integer.valueOf(10));

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(Integer.valueOf(3));

        Set<OrderItem> items = new HashSet<>();
        items.add(item);

        Order order = new Order();
        order.setId(Long.valueOf(1));
        order.setUser(user);
        order.setOrderItems(items);
        order.setStatus("PENDING");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Order placedOrder = orderService.placeOrder(order);

        // Assert
        assertNotNull(placedOrder);
        assertEquals(order.getStatus(), placedOrder.getStatus());
        assertEquals(7, product.getStock());
        verify(orderRepository).save(any(Order.class));
        verify(productRepository).save(product);
    }
}
