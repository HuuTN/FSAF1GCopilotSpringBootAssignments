package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Product;
import com.example.demo.exception.OrderCannotBeCanceledException;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// this is testcontainer, jpa test 

@SpringBootTest
public class OrderServiceTddTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    // setup code to create user, product, and a PENDING order
    @Test
    public void testCreatePendingOrder() {
        // Given
        Long productId = 1L; // Replace with a valid product ID

        Product product = new Product();
        product.setId(productId);
        product.setStock(10); // Assuming the product has enough stock
      
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        orderItem.setStatus(OrderStatus.PENDING);
        order.setOrderItems(Collections.singletonList(orderItem));
        // When
        Order createdOrder = orderService.createdOrder(order); // Assuming createOrder method exists

        // Then
        assertNotNull(createdOrder);
        assertEquals(OrderStatus.PENDING, ((OrderItem) createdOrder.getOrderItems()).getStatus());
    }
    
    @Test
    public void testCancelPendingOrder() {
        // Given
        Long orderId = 1L; // Replace with a valid order ID
        Order order = orderService.getOrderById(orderId);
        assertNotNull(order);
        assertEquals(OrderStatus.PENDING, ((OrderItem) order.getOrderItems()).getStatus());

        // When
        orderService.cancelOrder(orderId);

        // Then
        Order canceledOrder = orderService.getOrderById(orderId);
        assertNotNull(canceledOrder);
        assertEquals(OrderStatus.CANCELED, ((OrderItem) canceledOrder.getOrderItems()).getStatus());
        assertTrue(canceledOrder.getOrderItems().stream()
                .allMatch(item -> item.getStatus() == OrderStatus.CANCELED));

        canceledOrder.getOrderItems().forEach(item -> {
            assertEquals(OrderStatus.CANCELED, item.getStatus());
            assertTrue(productService.getProductById(item.getProduct().getId()).getStock() > 0); // Assuming getProductById method exists
        });
    }

    @Test
    // when canceling or pending order then status is canceledand stock is restored
    public void testCancelNonPendingOrder() {
        Order testOrder = new Order();
        testOrder.setId(1L); // Replace with a valid order ID
        // Given
        Long orderId = testOrder.getId(); // Replace with a valid order ID that is not PENDING
        int initialStock = productService.getProductById(1L).getStock(); // Assuming product ID 1 exists

        // Act
        orderService.cancelOrder(orderId);

// Assert

        // Verify that the order status is CANCELED and stock is restored
        assertEquals(OrderStatus.CANCELED, ((OrderItem) orderService.getOrderById(orderId).getOrderItems()).getStatus());
        assertTrue(productService.getProductById(1L).getStock() > initialStock); // Assuming product ID 1 exists
        assertTrue(orderService.getOrderById(orderId).getOrderItems().stream()
                .allMatch(item -> item.getStatus() == OrderStatus.CANCELED));
    }

}
