package com.example.lab4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.lab4.dto.OrderItemDTO;
import com.example.lab4.entity.Order;
import com.example.lab4.entity.OrderItem;
import com.example.lab4.entity.Product;
import com.example.lab4.exception.ResourceNotFoundException;
import com.example.lab4.repository.OrderItemRepository;
import com.example.lab4.repository.OrderRepository;
import com.example.lab4.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderItemServiceTest {
    @InjectMocks
    private OrderItemService orderItemService;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Pageable pageable = mock(Pageable.class);
        Order order = new Order(); order.setId(1L);
        Product product = new Product(); product.setId(2L);
        OrderItem item = new OrderItem();
        item.setId(3L); item.setOrder(order); item.setProduct(product);
        when(orderItemRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(item)));
        Page<OrderItemDTO> result = orderItemService.getAll(pageable);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetById_found() {
        OrderItem item = new OrderItem();
        Order order = new Order();
        order.setId(1L);
        Product product = new Product();
        product.setId(2L);
        item.setId(3L);
        item.setOrder(order);
        item.setProduct(product);
        when(orderItemRepository.findById(3L)).thenReturn(Optional.of(item));
        var result = orderItemService.getById(3L);
        assertNotNull(result);
        assertEquals(3L, result.getId());
    }

    @Test
    void testGetById_notFound() {
        when(orderItemRepository.findById(4L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(ResourceNotFoundException.class, () -> orderItemService.getById(4L));
        assertTrue(ex.getMessage().contains("OrderItem not found"));
    }

    @Test
    void testCreate_success() {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderId(1L); dto.setProductId(2L); dto.setQuantity(3); dto.setPrice(java.math.BigDecimal.valueOf(100.0));
        Order order = new Order(); order.setId(1L);
        Product product = new Product(); product.setId(2L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product));
        OrderItem saved = new OrderItem();
        saved.setId(10L); saved.setOrder(order); saved.setProduct(product);
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(saved);
        var result = orderItemService.create(dto);
        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    @Test
    void testCreate_orderNotFound() {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderId(1L);
        dto.setProductId(2L);
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(ResourceNotFoundException.class, () -> orderItemService.create(dto));
        assertTrue(ex.getMessage().contains("Order not found"));
    }

    @Test
    void testCreate_productNotFound() {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderId(1L);
        dto.setProductId(2L);
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(ResourceNotFoundException.class, () -> orderItemService.create(dto));
        assertTrue(ex.getMessage().contains("Product not found"));
    }

    @Test
    void testUpdate_found() {
        OrderItem item = new OrderItem();
        Order order = new Order(); order.setId(1L);
        Product product = new Product(); product.setId(2L);
        item.setId(3L); item.setOrder(order); item.setProduct(product);
        when(orderItemRepository.findById(3L)).thenReturn(Optional.of(item));
        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderId(1L); dto.setProductId(2L); dto.setQuantity(5); dto.setPrice(java.math.BigDecimal.valueOf(200.0));
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(item);
        var result = orderItemService.update(3L, dto);
        assertNotNull(result);
        assertEquals(3L, result.getId());
    }

    @Test
    void testUpdate_notFound() {
        when(orderItemRepository.findById(4L)).thenReturn(Optional.empty());
        OrderItemDTO dto = new OrderItemDTO();
        Exception ex = assertThrows(ResourceNotFoundException.class, () -> orderItemService.update(4L, dto));
        assertTrue(ex.getMessage().contains("OrderItem not found"));
    }

    @Test
    void testDelete_found() {
        when(orderItemRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orderItemRepository).deleteById(1L);
        orderItemService.delete(1L);
        verify(orderItemRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_notFound() {
        when(orderItemRepository.existsById(2L)).thenReturn(false);
        Exception ex = assertThrows(ResourceNotFoundException.class, () -> orderItemService.delete(2L));
        assertTrue(ex.getMessage().contains("OrderItem not found"));
    }
}
