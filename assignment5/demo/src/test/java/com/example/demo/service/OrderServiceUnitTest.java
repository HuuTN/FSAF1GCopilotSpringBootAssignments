package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;


public class OrderServiceUnitTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private OrderServiceImpl orderService;


    @Test
    void testCancelPendingOrderRestoresStock() {
        // Arrange
        Product product = Product.builder().id(1L).name("Test Product").price(10.0).stock(5).build();
        OrderItem item = OrderItem.builder().product(product).quantity(2).price(10.0).build();
        List<OrderItem> items = new ArrayList<>();
        items.add(item);
        Order order = Order.builder().id(1L).status(OrderStatus.PENDING).items(items).build();
        item.setOrder(order);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        orderService.cancelOrder(1L);

        // Assert
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELLED);
        assertThat(product.getStock()).isEqualTo(7); // 5 + 2
        verify(productRepository, times(1)).save(product);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testCancelOrderThrowsIfNotPending() {
        Order order = Order.builder().id(2L).status(OrderStatus.COMPLETED).build();
        when(orderRepository.findById(2L)).thenReturn(Optional.of(order));
        assertThatThrownBy(() -> orderService.cancelOrder(2L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Only pending orders can be cancelled");
    }

    @Test
    void testCancelOrderThrowsIfNotFound() {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> orderService.cancelOrder(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Order not found with id: 99");
    }

    @Test
    void testPlaceOrderReducesStockAndSavesOrder() {
        // Arrange
        Product product = Product.builder().id(1L).name("Test Product").price(10.0).stock(10).build();
        OrderItem item = OrderItem.builder().product(product).quantity(3).price(10.0).build();
        List<OrderItem> items = new ArrayList<>();
        items.add(item);
        Order order = Order.builder().id(1L).status(OrderStatus.PENDING).items(items).build();
        item.setOrder(order);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        orderService.placeOrder(order);

        // Assert
        assertThat(product.getStock()).isEqualTo(7); // 10 - 3
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
        verify(orderRepository, times(1)).save(order);
    }
}
