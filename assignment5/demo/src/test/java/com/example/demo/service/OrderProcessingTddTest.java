package com.example.demo.service;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

public class OrderProcessingTddTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderServiceImpl orderService;



    @Test
    void whenOrderIsPlaced_statusShouldBeProcessing() {
        User user = User.builder().id(1L).name("TestUser").email("test@example.com").password("pass").build();
        Product product = Product.builder().id(1L).name("TestProduct").description("desc").price(10.0).stock(5).build();
        OrderItem item = OrderItem.builder().product(product).quantity(1).price(10.0).build();
        Order order = Order.builder().user(user).items(Collections.singletonList(item)).build();
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order result = orderService.processOrder(order);
        assertEquals(OrderStatus.PROCESSING, result.getStatus());
    }
}
