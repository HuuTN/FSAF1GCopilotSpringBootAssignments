package com.example.lab4.service;

import com.example.lab4.model.dto.OrderItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {

    Page<OrderItemDTO> getAll(Pageable pageable);

    OrderItemDTO getById(Long id);

    OrderItemDTO create(OrderItemDTO dto);

    OrderItemDTO update(Long id, OrderItemDTO dto);

    void delete(Long id);
}