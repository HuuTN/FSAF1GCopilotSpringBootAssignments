package com.example.demojpa.service;

import com.example.demojpa.dto.CreateOrderRequestDTO;
import com.example.demojpa.entity.Order;
import com.example.demojpa.entity.Product;
import com.example.demojpa.repository.OrderRepository;
import com.example.demojpa.repository.ProductRepository;
import com.example.demojpa.repository.UserRepository;
import com.example.demojpa.exception.InsufficientStockException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnitTest {
    @Mock private OrderRepository orderRepository;
    @Mock private ProductRepository productRepository;
    @Mock private UserRepository userRepository;
    @InjectMocks private com.example.demojpa.service.impl.OrderServiceImpl orderService;

    @Test
    void testPlaceOrder_whenStockIsInsufficient_thenThrowsException() {
        CreateOrderRequestDTO request = new CreateOrderRequestDTO();
        Product outOfStockProduct = new Product();
        outOfStockProduct.setStock(0);

        when(productRepository.findById(any())).thenReturn(Optional.of(outOfStockProduct));

        assertThrows(InsufficientStockException.class, () -> {
            orderService.placeOrder(request);
        });

        verify(orderRepository, never()).save(any(Order.class));
    }
}
