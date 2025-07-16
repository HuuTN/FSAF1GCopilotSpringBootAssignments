package com.example.lab4.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;

import com.example.lab4.model.dto.OrderDTO;
import com.example.lab4.model.entity.*;
import com.example.lab4.model.enums.OrderStatus;
import com.example.lab4.repository.OrderRepository;
import com.example.lab4.repository.ProductRepository;
import com.example.lab4.repository.UserRepository;
import com.example.lab4.service.OrderService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

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
            order.setStatus(OrderStatus.PENDING);
        }
        order.setTotalAmount(new BigDecimal("199.98"));
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

     @Transactional
    public void cancelOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) throw new RuntimeException("Order not found");
        Order order = optionalOrder.get();
        if (order.getStatus() != OrderStatus.PENDING) throw new RuntimeException("Order cannot be cancelled");
        order.setStatus(OrderStatus.CANCELLED);
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }
        orderRepository.save(order);
    }

}
