package com.example.demo;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceUnitTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnAllOrders() {
        Order order1 = new Order();
        Order order2 = new Order();
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
        List<Order> orders = orderService.findAll();
        assertEquals(2, orders.size());
        verify(orderRepository).findAll();
    }

    @Test
    void findByUserId_shouldReturnOrdersForUser() {
        Long userId = 1L;
        Order order = new Order();
        when(orderRepository.findByUserId(userId)).thenReturn(Collections.singletonList(order));
        List<Order> orders = orderService.findByUserId(userId);
        assertEquals(1, orders.size());
        verify(orderRepository).findByUserId(userId);
    }

    @Test
    void findByOrderDateAfter_shouldReturnOrdersAfterDate() {
        LocalDateTime date = LocalDateTime.now();
        Order order = new Order();
        when(orderRepository.findByOrderDateAfter(date)).thenReturn(Collections.singletonList(order));
        List<Order> orders = orderService.findByOrderDateAfter(date);
        assertEquals(1, orders.size());
        verify(orderRepository).findByOrderDateAfter(date);
    }

    @Test
    void findOrdersWithMinItems_shouldReturnOrdersWithEnoughItems() {
        int minItems = 2;
        Order order = new Order();
        when(orderRepository.findOrdersWithMinItems(minItems)).thenReturn(Collections.singletonList(order));
        List<Order> orders = orderService.findOrdersWithMinItems(minItems);
        assertEquals(1, orders.size());
        verify(orderRepository).findOrdersWithMinItems(minItems);
    }

    @Test
    void cancelOrder_shouldSetOrderStatusToCancelled() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setStatus("NEW");
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));
        Order cancelledOrder = orderService.cancelOrder(orderId);
        assertNotNull(cancelledOrder);
        assertEquals("CANCELLED", cancelledOrder.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void cancelOrder_shouldThrowExceptionIfOrderNotFound() {
        Long orderId = 2L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> orderService.cancelOrder(orderId));
    }
}
