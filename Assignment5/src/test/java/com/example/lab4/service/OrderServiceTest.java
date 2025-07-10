package com.example.lab4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.lab4.dto.OrderDTO;
import com.example.lab4.entity.Order;
import com.example.lab4.entity.User;
import com.example.lab4.repository.OrderRepository;
import com.example.lab4.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Pageable pageable = mock(Pageable.class);
        List<Order> orders = List.of(new Order());
        when(orderRepository.findAll(pageable)).thenReturn(new PageImpl<>(orders));
        Page<OrderDTO> result = orderService.getAll(pageable);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetById_found() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        var result = orderService.getById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testGetById_notFound() {
        when(orderRepository.findById(2L)).thenReturn(Optional.empty());
        var result = orderService.getById(2L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreate() {
        OrderDTO dto = new OrderDTO();
        dto.setUserId(1L);
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Order saved = new Order();
        saved.setId(10L);
        saved.setUser(user);
        when(orderRepository.save(any(Order.class))).thenReturn(saved);
        var result = orderService.create(dto);
        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    @Test
    void testUpdate_found() {
        OrderDTO dto = new OrderDTO();
        dto.setUserId(1L);
        when(orderRepository.existsById(1L)).thenReturn(true);
        Order saved = new Order();
        saved.setId(1L);
        when(orderRepository.save(any(Order.class))).thenReturn(saved);
        var result = orderService.update(1L, dto);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testUpdate_notFound() {
        OrderDTO dto = new OrderDTO();
        when(orderRepository.existsById(2L)).thenReturn(false);
        var result = orderService.update(2L, dto);
        assertTrue(result.isEmpty());
    }

    @Test
    void testDelete_found() {
        when(orderRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(1L);
        assertTrue(orderService.delete(1L));
    }

    @Test
    void testDelete_notFound() {
        when(orderRepository.existsById(2L)).thenReturn(false);
        assertFalse(orderService.delete(2L));
    }

}
