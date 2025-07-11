package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTddTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void testCancelPendingOrderRestoresStock() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setStock(5);

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(2);

        Order order = new Order();
        order.setId(100L);
        order.setStatus("PENDING");
        order.setOrderItems(Arrays.asList(item));

        when(orderRepository.findById(100L)).thenReturn(Optional.of(order));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        orderService.cancelOrder(100L);

        // Assert
        assertEquals("CANCELLED", order.getStatus());
        assertEquals(7, product.getStock()); // 5 + 2
        verify(productRepository).save(product);
        verify(orderRepository).save(order);
    }

    @Test
    void testCancelNonExistentOrder() {
        // Arrange
        when(orderRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            orderService.cancelOrder(999L);
        });
        verify(productRepository, never()).save(any());
        verify(orderRepository, never()).save(any());
    }

    @Test
    void testCancelNonPendingOrder() {
        // Arrange
        Order order = new Order();
        order.setId(100L);
        order.setStatus("COMPLETED");

        when(orderRepository.findById(100L)).thenReturn(Optional.of(order));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            orderService.cancelOrder(100L);
        });
        verify(productRepository, never()).save(any());
        verify(orderRepository, never()).save(any());
    }

    @Test
    void testPlaceOrderSuccess() {
        // Arrange
        User user = new User();
        user.setId(1L);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setStock(10);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setStock(20);

        OrderItem item1 = new OrderItem();
        item1.setProduct(product1);
        item1.setQuantity(2);

        OrderItem item2 = new OrderItem();
        item2.setProduct(product2);
        item2.setQuantity(3);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Order order = orderService.placeOrder(1L, Arrays.asList(item1, item2));

        // Assert
        assertNotNull(order);
        assertEquals("PENDING", order.getStatus());
        assertEquals(user, order.getUser());
        assertEquals(2, order.getOrderItems().size());
        assertEquals(8, product1.getStock()); // 10 - 2
        assertEquals(17, product2.getStock()); // 20 - 3
        verify(productRepository, times(2)).save(any(Product.class));
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testPlaceOrderInsufficientStock() {
        // Arrange
        User user = new User();
        user.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setStock(5);

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(10); // Requesting more than available

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.placeOrder(1L, Arrays.asList(item));
        });
        assertTrue(exception.getMessage().contains("Insufficient stock"));
        assertEquals(5, product.getStock()); // Stock should remain unchanged
        verify(productRepository, never()).save(any());
        verify(orderRepository, never()).save(any());
    }

    @Test
    void testPlaceOrderUserNotFound() {
        // Arrange
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        OrderItem item = new OrderItem();
        item.setProduct(new Product());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.placeOrder(999L, Arrays.asList(item));
        });
        assertTrue(exception.getMessage().contains("User not found"));
        verify(productRepository, never()).save(any());
        verify(orderRepository, never()).save(any());
    }
}
