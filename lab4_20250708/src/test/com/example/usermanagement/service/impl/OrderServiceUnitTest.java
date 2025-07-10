import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.usermanagement.entity.Order;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.OrderRepository;
import com.example.usermanagement.repository.ProductRepository;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        User mockUser = new User();
        mockUser.setId(1L);

        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setStock(10);

        Order mockOrder = new Order();
        mockOrder.setUser(mockUser);
        mockOrder.addProduct(mockProduct);

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);

        // Act
        Order placedOrder = orderService.placeOrder(1L, 1L);

        // Assert
        assertNotNull(placedOrder);
        assertEquals(mockUser, placedOrder.getUser());
        assertTrue(placedOrder.getProducts().contains(mockProduct));
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
