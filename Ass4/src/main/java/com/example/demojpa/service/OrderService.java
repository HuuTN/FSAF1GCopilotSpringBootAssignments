package com.example.demojpa.service;

import com.example.demojpa.dto.OrderDTO;
import com.example.demojpa.entity.Order;
import com.example.demojpa.entity.Customer;
import com.example.demojpa.repository.OrderRepository;
import com.example.demojpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(this::toDTO);
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toDTO);
    }

    public OrderDTO createOrder(OrderDTO dto) {
        Order order = new Order();
        if (dto.getCustomerId() != null) {
            customerRepository.findById(dto.getCustomerId()).ifPresent(order::setCustomer);
        }
        return toDTO(orderRepository.save(order));
    }

    public Optional<OrderDTO> updateOrder(Long id, OrderDTO dto) {
        return orderRepository.findById(id).map(order -> {
            if (dto.getCustomerId() != null) {
                customerRepository.findById(dto.getCustomerId()).ifPresent(order::setCustomer);
            }
            return toDTO(orderRepository.save(order));
        });
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        if (order.getCustomer() != null) dto.setCustomerId(order.getCustomer().getId());
        return dto;
    }
}
