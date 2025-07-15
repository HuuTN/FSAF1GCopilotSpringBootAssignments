package com.example.demojpa.service.impl;

import com.example.demojpa.dto.OrderDTO;
import com.example.demojpa.dto.CreateOrderRequestDTO;
import com.example.demojpa.entity.Order;
import com.example.demojpa.entity.Product;
import com.example.demojpa.entity.OrderItem;
import com.example.demojpa.repository.OrderRepository;
import com.example.demojpa.repository.ProductRepository;
import com.example.demojpa.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(this::toDTO);
    }

    @Override
    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toDTO);
    }

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        Order order = new Order();
        order.setStatus(Order.Status.PENDING); // Đảm bảo luôn set status mặc định
        return toDTO(orderRepository.save(order));
    }

    @Override
    public Optional<OrderDTO> updateOrder(Long id, OrderDTO dto) {
        return orderRepository.findById(id).map(order -> {
            return toDTO(orderRepository.save(order));
        });
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getStatus() == Order.Status.PENDING) {
            order.setStatus(Order.Status.CANCELLED);
            if (order.getOrderItems() != null) {
                order.getOrderItems().forEach(item -> {
                    if (item.getProduct() != null) {
                        item.getProduct().setStock(item.getProduct().getStock() + item.getQuantity());
                    }
                });
            }
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Order is not pending");
        }
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        return dto;
    }


@Override
public Order placeOrder(CreateOrderRequestDTO request) {
    // Lấy thông tin sản phẩm
    Product product = productRepository.findById(request.getProductId()).orElse(null);
    if (product == null || request.getQuantity() < 1 || product.getStock() < request.getQuantity()) {
        throw new com.example.demojpa.exception.InsufficientStockException("Insufficient stock or product not found");
    }
    // Trừ tồn kho
    product.setStock(product.getStock() - request.getQuantity());
    productRepository.save(product);

    // Tạo order
    Order order = new Order();
    order.setStatus(Order.Status.PENDING);
    order.setOrderDate(java.time.LocalDateTime.now());
    // Nếu có userId trong request, set user cho order
    // (Bỏ qua nếu không có UserRepository)
    // Tạo order item
    OrderItem orderItem = new OrderItem();
    orderItem.setProduct(product);
    orderItem.setQuantity(request.getQuantity());
    orderItem.setPrice(product.getPrice());
    orderItem.setOrder(order);
    java.util.List<OrderItem> orderItems = new java.util.ArrayList<>();
    orderItems.add(orderItem);
    order.setOrderItems(orderItems);
    return orderRepository.save(order);
}

}