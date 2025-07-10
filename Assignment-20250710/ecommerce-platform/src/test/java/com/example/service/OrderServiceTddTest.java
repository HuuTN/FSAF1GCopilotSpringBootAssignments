// Write a test method to cancel an PENDING order. Chage the status to CANCELED and restore the stock of the products in the order.
package com.example.service;
import com.example.entity.Order;
import com.example.entity.OrderItem;
import com.example.constant.OrderStatus;
import com.example.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Optional;
import com.example.entity.Product;
import com.example.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTddTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(1L);
        product.setStock(10);

        order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);
        // Add 2 units of the product to the order using OrderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(2);
        order.setItems(java.util.List.of(orderItem));
    }

    @Test
    public void testCancelPendingOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        orderService.cancelOrder(1L);

        assertEquals(OrderStatus.CANCELED, order.getStatus());
        assertEquals(12, product.getStock()); // Stock should be restored to 12 (10 + 2)
        
        verify(orderRepository).save(order);
        verify(productRepository).save(product);
    }
}
