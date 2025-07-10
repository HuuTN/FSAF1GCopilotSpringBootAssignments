package com.example.service;

import com.example.entity.Order;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.entity.Customer;
import com.example.entity.Category;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.repository.CustomerRepository;
import com.example.constant.OrderStatus;
import com.example.exception.ResourceNotFoundException;
import com.example.dto.OrderItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnitTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User testUser;
    private Customer testCustomer;
    private Product testProduct1;
    private Product testProduct2;
    private Category testCategory;

    @BeforeEach
    void setUp() {
        // Setup test data
        testCategory = new Category();
        testCategory.setId(1L);
        testCategory.setName("Electronics");

        testUser = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .build();

        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setName("Jane Smith");
        testCustomer.setEmail("jane.smith@example.com");
        testCustomer.setPassword("password123");
        testCustomer.setAddress("123 Main St");
        testCustomer.setPhone("555-1234");

        testProduct1 = new Product();
        testProduct1.setId(1L);
        testProduct1.setName("Laptop");
        testProduct1.setPrice(new BigDecimal("999.99"));
        testProduct1.setStock(10);
        testProduct1.setCategory(testCategory);

        testProduct2 = new Product();
        testProduct2.setId(2L);
        testProduct2.setName("Mouse");
        testProduct2.setPrice(new BigDecimal("29.99"));
        testProduct2.setStock(5);
        testProduct2.setCategory(testCategory);
    }

    @Test
    void testPlaceOrder_Success() {
        // Arrange
        Long userId = 1L;
        Long customerId = 1L;
        List<OrderItemDTO> orderItems = List.of(
                new OrderItemDTO(1L, 2),
                new OrderItemDTO(2L, 1)
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(testCustomer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(testProduct2));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setId(1L);
            return order;
        });

        // Act
        Order result = orderService.placeOrder(userId, customerId, orderItems);

        // Assert
        assertNotNull(result);
        assertEquals(testCustomer.getName(), result.getCustomer().getName());
        assertEquals(testCustomer.getEmail(), result.getCustomer().getEmail());
        assertEquals(OrderStatus.PENDING, result.getStatus());
        assertEquals(testUser, result.getUser());
        assertEquals(testCustomer, result.getCustomer());
        assertEquals(2, result.getItems().size());
        
        // Verify total amount calculation: (999.99 * 2) + (29.99 * 1) = 2029.97
        BigDecimal expectedTotal = new BigDecimal("999.99").multiply(new BigDecimal("2"))
                .add(new BigDecimal("29.99").multiply(new BigDecimal("1")));
        assertEquals(expectedTotal, result.getTotalAmount());

        // Verify repository interactions
        verify(userRepository).findById(userId);
        verify(customerRepository).findById(customerId);
        verify(productRepository).findById(1L);
        verify(productRepository).findById(2L);
        verify(productRepository, times(2)).save(any(Product.class));
        verify(orderRepository).save(any(Order.class));

        // Verify stock updates
        assertEquals(8, testProduct1.getStock()); // 10 - 2 = 8
        assertEquals(4, testProduct2.getStock()); // 5 - 1 = 4
    }

    @Test
    void testPlaceOrder_UserNotFound() {
        // Arrange
        Long userId = 99L;
        Long customerId = 1L;
        List<OrderItemDTO> orderItems = List.of(new OrderItemDTO(1L, 1));

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.placeOrder(userId, customerId, orderItems)
        );

        assertEquals("User not found with id: 99", exception.getMessage());
        verify(userRepository).findById(userId);
        verifyNoInteractions(customerRepository);
        verifyNoInteractions(productRepository);
        verifyNoInteractions(orderRepository);
    }

    @Test
    void testPlaceOrder_CustomerNotFound() {
        // Arrange
        Long userId = 1L;
        Long customerId = 99L;
        List<OrderItemDTO> orderItems = List.of(new OrderItemDTO(1L, 1));

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.placeOrder(userId, customerId, orderItems)
        );

        assertEquals("Customer not found with id: 99", exception.getMessage());
        verify(userRepository).findById(userId);
        verify(customerRepository).findById(customerId);
        verifyNoInteractions(productRepository);
        verifyNoInteractions(orderRepository);
    }

    @Test
    void testPlaceOrder_ProductNotFound() {
        // Arrange
        Long userId = 1L;
        Long customerId = 1L;
        List<OrderItemDTO> orderItems = List.of(new OrderItemDTO(99L, 1));

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(testCustomer));
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.placeOrder(userId, customerId, orderItems)
        );

        assertEquals("Product not found with id: 99", exception.getMessage());
        verify(userRepository).findById(userId);
        verify(customerRepository).findById(customerId);
        verify(productRepository).findById(99L);
        verifyNoInteractions(orderRepository);
    }

    @Test
    void testPlaceOrder_InsufficientStock() {
        // Arrange
        Long userId = 1L;
        Long customerId = 1L;
        List<OrderItemDTO> orderItems = List.of(new OrderItemDTO(1L, 15)); // Request more than available stock

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(testCustomer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct1));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.placeOrder(userId, customerId, orderItems)
        );

        assertEquals("Insufficient stock for product: Laptop", exception.getMessage());
        verify(userRepository).findById(userId);
        verify(customerRepository).findById(customerId);
        verify(productRepository).findById(1L);
        verify(productRepository, never()).save(any(Product.class));
        verifyNoInteractions(orderRepository);
    }

    @Test
    void testPlaceOrder_EmptyOrderItems() {
        // Arrange
        Long userId = 1L;
        Long customerId = 1L;
        List<OrderItemDTO> orderItems = new ArrayList<>();

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(testCustomer));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setId(1L);
            return order;
        });

        // Act
        Order result = orderService.placeOrder(userId, customerId, orderItems);

        // Assert
        assertNotNull(result);
        assertEquals(testCustomer.getName(), result.getCustomer().getName());
        assertEquals(testCustomer.getEmail(), result.getCustomer().getEmail());
        assertEquals(OrderStatus.PENDING, result.getStatus());
        assertEquals(BigDecimal.ZERO, result.getTotalAmount());
        assertTrue(result.getItems().isEmpty());

        verify(userRepository).findById(userId);
        verify(customerRepository).findById(customerId);
        verify(orderRepository).save(any(Order.class));
        verifyNoInteractions(productRepository);
    }

    @Test
    void testPlaceOrder_MultipleItemsSameProduct() {
        // Arrange
        Long userId = 1L;
        Long customerId = 1L;
        List<OrderItemDTO> orderItems = List.of(
                new OrderItemDTO(1L, 3),
                new OrderItemDTO(1L, 2) // Same product, different quantities
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(testCustomer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct1));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setId(1L);
            return order;
        });

        // Act
        Order result = orderService.placeOrder(userId, customerId, orderItems);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getItems().size());
        
        // Total: (999.99 * 3) + (999.99 * 2) = 4999.95
        BigDecimal expectedTotal = new BigDecimal("999.99").multiply(new BigDecimal("5"));
        assertEquals(expectedTotal, result.getTotalAmount());

        // Stock should be reduced by total quantity (3 + 2 = 5)
        assertEquals(5, testProduct1.getStock()); // 10 - 5 = 5

        verify(userRepository).findById(userId);
        verify(customerRepository).findById(customerId);
        verify(productRepository, times(2)).findById(1L);
        verify(productRepository, times(2)).save(testProduct1);
        verify(orderRepository).save(any(Order.class));
    }
}

