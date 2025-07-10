package com.example.demo.service;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.BaseIntegrationTest;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

@Transactional
public class OrderServiceTddTest extends BaseIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    @Test
    void testCancelPendingOrderRestoresStock() {
        // Arrange: create a product and a pending order with an order item
        Product product = Product.builder().name("Test Product").price(10.0).stock(5).build();
        product = productRepository.save(product);

        OrderItem item = OrderItem.builder().product(product).quantity(2).price(10.0).build();
        Order order = Order.builder().status(OrderStatus.PENDING).items(new java.util.ArrayList<>(List.of(item))).build();
        item.setOrder(order);
        order = orderRepository.save(order);
        int originalStock = product.getStock();

        // Simulate order placement: decrement product stock
        product.setStock(product.getStock() - item.getQuantity());
        productRepository.save(product);

        // Act: cancel the order
        orderService.cancelOrder(order.getId());

        // Assert: order status is CANCELLED and product stock is restored
        Order cancelledOrder = orderRepository.findById(order.getId()).orElseThrow();
        assertThat(cancelledOrder.getStatus()).isEqualTo(OrderStatus.CANCELLED);
        Product updatedProduct = productRepository.findById(product.getId()).orElseThrow();
        assertThat(updatedProduct.getStock()).isEqualTo(originalStock);
    }
}
