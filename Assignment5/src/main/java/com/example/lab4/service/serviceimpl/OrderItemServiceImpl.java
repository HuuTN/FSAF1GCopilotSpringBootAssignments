package com.example.lab4.service.serviceimpl;

import com.example.lab4.model.dto.OrderItemDTO;
import com.example.lab4.model.entity.OrderItem;
import com.example.lab4.model.entity.Order;
import com.example.lab4.model.entity.Product;
import com.example.lab4.exception.ResourceNotFoundException;
import com.example.lab4.repository.OrderItemRepository;
import com.example.lab4.repository.OrderRepository;
import com.example.lab4.repository.ProductRepository;
import com.example.lab4.service.OrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public Page<OrderItemDTO> getAll(Pageable pageable) {
        return orderItemRepository.findAll(pageable).map(this::toDTO);
    }

    public OrderItemDTO getById(Long id) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        return toDTO(item);
    }

    public OrderItemDTO create(OrderItemDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + dto.getOrderId()));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + dto.getProductId()));
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());
        return toDTO(orderItemRepository.save(item));
    }

    public OrderItemDTO update(Long id, OrderItemDTO dto) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        if (!item.getOrder().getId().equals(dto.getOrderId())) {
            Order order = orderRepository.findById(dto.getOrderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + dto.getOrderId()));
            item.setOrder(order);
        }
        if (!item.getProduct().getId().equals(dto.getProductId())) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + dto.getProductId()));
            item.setProduct(product);
        }
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());
        return toDTO(orderItemRepository.save(item));
    }

    public void delete(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("OrderItem not found with id: " + id);
        }
        orderItemRepository.deleteById(id);
    }

    private OrderItemDTO toDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setOrderId(item.getOrder().getId());
        dto.setProductId(item.getProduct().getId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }
}
