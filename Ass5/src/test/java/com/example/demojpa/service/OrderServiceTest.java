package com.example.demojpa.service;

import com.example.demojpa.dto.CreateOrderRequestDTO;
import com.example.demojpa.dto.OrderDTO;
import com.example.demojpa.entity.Order;
import com.example.demojpa.entity.OrderItem;
import com.example.demojpa.entity.Product;
import com.example.demojpa.entity.User;
import com.example.demojpa.exception.InsufficientStockException;
import com.example.demojpa.repository.OrderRepository;
import com.example.demojpa.repository.ProductRepository;
import com.example.demojpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCancelOrder_success() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(Order.Status.PENDING);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        orderService.cancelOrder(1L);
        assertEquals(Order.Status.CANCELLED, order.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void testCancelOrder_notFound() {
        when(orderRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> orderService.cancelOrder(2L));
    }

    @Test
    void testCancelOrder_notPending() {
        Order order = new Order();
        order.setId(3L);
        order.setStatus(Order.Status.COMPLETED);
        when(orderRepository.findById(3L)).thenReturn(Optional.of(order));
        assertThrows(RuntimeException.class, () -> orderService.cancelOrder(3L));
        verify(orderRepository, never()).save(order);
    }

    @Test
    void testPlaceOrder_insufficientStock() {
        CreateOrderRequestDTO req = new CreateOrderRequestDTO();
        req.setUserId(1L);
        req.setProductId(1L);
        req.setQuantity(10);
        Product product = new Product();
        product.setId(1L);
        product.setStockQuantity(5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        assertThrows(InsufficientStockException.class, () -> orderService.placeOrder(req));
    }

    @Test
    void testPlaceOrder_productNotFound() {
        CreateOrderRequestDTO req = new CreateOrderRequestDTO();
        req.setUserId(1L);
        req.setProductId(2L);
        req.setQuantity(1);
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> orderService.placeOrder(req));
    }

    @Test
    void testPlaceOrder_userNotFound() {
        CreateOrderRequestDTO req = new CreateOrderRequestDTO();
        req.setUserId(99L);
        req.setProductId(1L);
        req.setQuantity(1);
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> orderService.placeOrder(req));
    }

    @Test
    void testPlaceOrder_success() {
        CreateOrderRequestDTO req = new CreateOrderRequestDTO();
        req.setUserId(1L);
        req.setProductId(1L);
        req.setQuantity(2);
        Product product = new Product();
        product.setId(1L);
        product.setStockQuantity(5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(inv -> {
            Order o = inv.getArgument(0);
            o.setId(10L);
            return o;
        });
        Order order = orderService.placeOrder(req);
        assertNotNull(order.getId());
        assertEquals(Order.Status.PENDING, order.getStatus());
        assertEquals(3, product.getStockQuantity());
    }

    @Test
    void testGetOrderById_found() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Optional<OrderDTO> result = orderService.getOrderById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testGetOrderById_notFound() {
        when(orderRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<OrderDTO> result = orderService.getOrderById(2L);
        assertFalse(result.isPresent());
    }

    @Test
    void testGetAllOrders() {
        Order order = new Order();
        order.setId(1L);
        org.springframework.data.domain.Page<Order> page = new org.springframework.data.domain.PageImpl<>(Collections.singletonList(order));
        when(orderRepository.findAll(any(org.springframework.data.domain.Pageable.class))).thenReturn(page);
        var result = orderService.getAllOrders(org.springframework.data.domain.PageRequest.of(0, 10));
        assertEquals(1, result.getTotalElements());
        assertEquals(1L, result.getContent().get(0).getId());
    }
}
