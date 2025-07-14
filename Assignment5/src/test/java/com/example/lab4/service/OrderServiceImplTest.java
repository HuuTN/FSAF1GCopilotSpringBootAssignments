package com.example.lab4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.lab4.model.entity.*;
import com.example.lab4.model.enums.OrderStatus;
import com.example.lab4.repository.OrderRepository;
import com.example.lab4.repository.ProductRepository;
import com.example.lab4.service.serviceimpl.OrderServiceImpl;

import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOrderServiceImplNotNull() {
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        assertNotNull(orderServiceImpl);
    }

    @Test
    void testCancelOrder_success() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);
        Product product = new Product();
        product.setId(2L);
        product.setStock(5);
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(2);
        order.setOrderItems(List.of(item));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        orderServiceImpl.cancelOrder(1L);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        assertEquals(7, product.getStock());
    }

    @Test
    void testCancelOrder_notFound() {
        when(orderRepository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(RuntimeException.class, () -> orderServiceImpl.cancelOrder(2L));
        assertTrue(ex.getMessage().contains("Order not found"));
    }

    @Test
    void testCancelOrder_wrongStatus() {
        Order order = new Order();
        order.setId(3L);
        order.setStatus(OrderStatus.DONE);
        when(orderRepository.findById(3L)).thenReturn(Optional.of(order));
        Exception ex = assertThrows(RuntimeException.class, () -> orderServiceImpl.cancelOrder(3L));
        assertTrue(ex.getMessage().contains("Order cannot be cancelled"));
    }
}
