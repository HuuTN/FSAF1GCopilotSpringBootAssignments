package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTddTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void cancelPendingOrder_shouldSetStatusToCancelledAndRestoreStock() {
        MockitoAnnotations.openMocks(this);
        // Arrange
        Product product = new Product();
        product.setId(Long.valueOf(1));
        product.setName("Test Product");
        product.setStock(Integer.valueOf(5));

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(Integer.valueOf(2));

        Set<OrderItem> items = new HashSet<>();
        items.add(item);

        Order order = new Order();
        order.setId(Long.valueOf(1));
        order.setOrderItems(items);
        order.setStatus("PENDING");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        orderService.cancelOrder(1L);

        // Assert
        assertEquals("CANCELLED", order.getStatus());
        assertEquals(7, product.getStock());
    }
}
