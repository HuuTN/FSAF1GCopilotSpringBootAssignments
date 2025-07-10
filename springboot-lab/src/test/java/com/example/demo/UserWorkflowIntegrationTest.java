package com.example.demo;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.entity.Product;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.OrderItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class UserWorkflowIntegrationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    @DisplayName("Full user workflow: create user, product, place order")
    void userWorkflow() {
        // Create user
        User user = new User();
        user.setUsername("workflowuser");
        user.setEmail("workflow@example.com");
        user = userRepository.save(user);
        Assertions.assertNotNull(user.getId());

        // Create product
        Product product = new Product();
        product.setName("Workflow Product");
        product.setPrice(99.99);
        product = productRepository.save(product);
        Assertions.assertNotNull(product.getId());

        // Place order
        Order order = new Order();
        order.setUser(user);
        order.setStatus("NEW");
        order = orderRepository.save(order);
        Assertions.assertNotNull(order.getId());

        // Add order item
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(2);
        item = orderItemRepository.save(item);
        Assertions.assertNotNull(item.getId());

        // Fetch and verify
        List<Order> userOrders = orderRepository.findByUserId(user.getId());
        Assertions.assertFalse(userOrders.isEmpty());
        Order fetchedOrder = userOrders.get(0);
        List<OrderItem> items = orderItemRepository.findByOrderId(fetchedOrder.getId());
        Assertions.assertEquals(1, items.size());
        Assertions.assertEquals("Workflow Product", items.get(0).getProduct().getName());
        Assertions.assertEquals(2, items.get(0).getQuantity());
    }
}
