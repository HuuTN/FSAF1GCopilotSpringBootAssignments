package testing;

import com.example.demo.controller.ApiController;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApiTest {
    @Mock ProductService productService;
    @Mock CategoryService categoryService;
    @Mock OrderService orderService;
    @Mock UserService userService;
    @Mock ReviewService reviewService;
    @Mock OrderItemService orderItemService;
    @InjectMocks ApiController apiController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    // Product APIs
    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(new Product());
        when(productService.findAll()).thenReturn(products);
        assertEquals(products, apiController.getAllProducts());
    }

    @Test
    void testSearchProducts() {
        String keyword = "test";
        List<Product> products = Arrays.asList(new Product());
        when(productService.searchByName(keyword)).thenReturn(products);
        assertEquals(products, apiController.searchProducts(keyword));
    }

    @Test
    void testGetExpensiveProducts() {
        double minPrice = 100.0;
        List<Product> products = Arrays.asList(new Product());
        when(productService.findExpensiveProducts(minPrice)).thenReturn(products);
        assertEquals(products, apiController.getExpensiveProducts(minPrice));
    }

    @Test
    void testGetProductsPage() {
        Pageable pageable = mock(Pageable.class);
        Page<Product> page = mock(Page.class);
        when(productService.findAll(pageable)).thenReturn(page);
        assertEquals(page, apiController.getProductsPage(pageable));
    }

    @Test
    void testFilterProducts() {
        List<Product> products = Arrays.asList(new Product());
        when(productService.findAll(any(Specification.class))).thenReturn(products);
        assertEquals(products, apiController.filterProducts(null, null, null, null));
    }

    // Category APIs
    @Test
    void testGetAllCategories() {
        List<Category> categories = Arrays.asList(new Category());
        when(categoryService.findAll()).thenReturn(categories);
        assertEquals(categories, apiController.getAllCategories());
    }

    @Test
    void testGetRootCategories() {
        List<Category> categories = Arrays.asList(new Category());
        when(categoryService.findRootCategories()).thenReturn(categories);
        assertEquals(categories, apiController.getRootCategories());
    }

    // Order APIs
    @Test
    void testGetAllOrders() {
        List<Order> orders = Arrays.asList(new Order());
        when(orderService.findAll()).thenReturn(orders);
        assertEquals(orders, apiController.getAllOrders());
    }

    @Test
    void testGetOrdersByUser() {
        Long userId = 1L;
        List<Order> orders = Arrays.asList(new Order());
        when(orderService.findByUserId(userId)).thenReturn(orders);
        assertEquals(orders, apiController.getOrdersByUser(userId));
    }

    // User APIs
    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(new User());
        when(userService.findAll()).thenReturn(users);
        assertEquals(users, apiController.getAllUsers());
    }

    @Test
    void testGetUser() {
        Long id = 1L;
        User user = new User();
        when(userService.findById(id)).thenReturn(user);
        assertEquals(user, apiController.getUser(id));
    }

    // Review APIs
    @Test
    void testGetAllReviews() {
        List<Review> reviews = Arrays.asList(new Review());
        when(reviewService.findAll()).thenReturn(reviews);
        assertEquals(reviews, apiController.getAllReviews());
    }

    @Test
    void testGetReviewsByProduct() {
        Long productId = 1L;
        List<Review> reviews = Arrays.asList(new Review());
        when(reviewService.findByProductId(productId)).thenReturn(reviews);
        assertEquals(reviews, apiController.getReviewsByProduct(productId));
    }

    // OrderItem APIs
    @Test
    void testGetOrderItemsByOrder() {
        Long orderId = 1L;
        List<OrderItem> items = Arrays.asList(new OrderItem());
        when(orderItemService.findByOrderId(orderId)).thenReturn(items);
        assertEquals(items, apiController.getOrderItemsByOrder(orderId));
    }

    // Order Processing API
    @Test
    void testProcessOrder() {
        Long orderId = 1L;
        doNothing().when(orderService).processOrder(orderId);
        assertDoesNotThrow(() -> apiController.processOrder(orderId));
        verify(orderService, times(1)).processOrder(orderId);
    }
}
