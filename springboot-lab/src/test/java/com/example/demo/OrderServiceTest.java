package com.example.demo;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private com.example.demo.service.impl.OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
