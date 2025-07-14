package com.example.lab4.service;

import com.example.lab4.model.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService {

    Page<OrderDTO> getAll(Pageable pageable);

    Optional<OrderDTO> getById(Long id);

    OrderDTO create(OrderDTO dto);

    Optional<OrderDTO> update(Long id, OrderDTO dto);

    boolean delete(Long id);

    void cancelOrder(Long orderId);
}