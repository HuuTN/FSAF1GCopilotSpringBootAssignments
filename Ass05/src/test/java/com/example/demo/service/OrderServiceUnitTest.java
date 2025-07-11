package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void testPlaceOrderSuccess() {
        // Arrange
        User user = new User();
        user.setId(1L);
        Product product = new Product();
        product.setId(2L);
        product.setStock(10);
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(3);
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(Arrays.asList(item));
        order.setStatus("PENDING");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Order placedOrder = orderService.placeOrder(1L, Arrays.asList(item));

        // Assert
        assertNotNull(placedOrder);
        assertEquals("PENDING", placedOrder.getStatus());
        assertEquals(1, placedOrder.getOrderItems().size());
        assertEquals(7, product.getStock()); // 10 - 3
        verify(orderRepository).save(any(Order.class));
        verify(productRepository).save(product);
    }
}
