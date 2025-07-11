import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.serviceImplement.OrderServiceImpl;

import java.util.Collections;
import java.util.Optional;

public class OrderServiceUnitTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder() {
        // Arrange
        User user = new User();
        user.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setStock(10);

        Order order = new Order();
        OrderItem mockOrderItem = new OrderItem();
        mockOrderItem.setProduct(product);
        order.setCustomerName(user.getName());
        order.setOrderItems(Collections.singletonList(mockOrderItem));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        Order createdOrder = orderService.createdOrder(order);

        // Assert
        assertNotNull(createdOrder);
        assertEquals("Test Customer", createdOrder.getCustomerName());
        assertEquals(1, createdOrder.getOrderItems().size());
        assertTrue(createdOrder.getOrderItems().get(0).getProduct().equals(product));
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}