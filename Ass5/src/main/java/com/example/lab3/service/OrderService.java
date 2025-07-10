package com.example.lab3.service;

import com.example.lab3.dto.OrderDTO;
import com.example.lab3.entity.Order;
import com.example.lab3.entity.User;
import com.example.lab3.repository.OrderRepository;
import com.example.lab3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        if (order.getOrderItems() != null) {
            dto.setOrderItemIds(order.getOrderItems().stream().map(item -> item.getId()).toList());
        } else {
            dto.setOrderItemIds(new java.util.ArrayList<>());
        }
        if (order.getStatus() != null) {
            dto.setStatus(order.getStatus().name());
        }
        return dto;
    }

    private Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId()).orElse(null);
            order.setUser(user);
        }
        return order;
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> getAll(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        return new PageImpl<>(
            page.getContent().stream().map(this::toDTO).collect(Collectors.toList()),
            pageable,
            page.getTotalElements()
        );
    }

    public Optional<OrderDTO> getById(Long id) {
        return orderRepository.findById(id).map(this::toDTO);
    }

    public OrderDTO create(OrderDTO dto) {
        Order order = toEntity(dto);
        if (order.getStatus() == null) {
            order.setStatus(com.example.lab3.entity.OrderStatus.PENDING);
        }
        Order saved = orderRepository.save(order);
        return toDTO(saved);
    }

    public Optional<OrderDTO> update(Long id, OrderDTO dto) {
        if (!orderRepository.existsById(id)) return Optional.empty();
        Order order = toEntity(dto);
        order.setId(id);
        Order saved = orderRepository.save(order);
        return Optional.of(toDTO(saved));
    }

    public boolean delete(Long id) {
        if (!orderRepository.existsById(id)) return false;
        orderRepository.deleteById(id);
        return true;
    }

    public void cancelOrder(Long orderId) {
        orderServiceImpl.cancelOrder(orderId);
    }
}
