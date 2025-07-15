package com.example.demojpa.service;

import com.example.demojpa.dto.OrderDTO;
import com.example.demojpa.dto.CreateOrderRequestDTO;
import com.example.demojpa.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public interface OrderService {
    Page<OrderDTO> getAllOrders(Pageable pageable);
    Optional<OrderDTO> getOrderById(Long id);
    OrderDTO createOrder(OrderDTO dto);
    Optional<OrderDTO> updateOrder(Long id, OrderDTO dto);
    void deleteOrder(Long id);
    void cancelOrder(Long orderId);
    Order placeOrder(CreateOrderRequestDTO request);
} 