package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.repository.specification.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired private com.example.demo.service.ProductService productService;
    @Autowired private com.example.demo.service.CategoryService categoryService;
    @Autowired private com.example.demo.service.OrderService orderService;
    @Autowired private com.example.demo.service.UserService userService;
    @Autowired private com.example.demo.service.ReviewService reviewService;
    @Autowired private com.example.demo.service.OrderItemService orderItemService;

    // --- CREATE APIs for E2E test ---
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PostMapping("/order-items")
    public OrderItem createOrderItem(@RequestBody OrderItem item) {
        return orderItemService.save(item);
    }
    // removed duplicate fields

    // Product APIs
    @GetMapping("/products")
    public List<Product> getAllProducts() { return productService.findAll(); }

    @GetMapping("/products/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productService.searchByName(keyword);
    }

    @GetMapping("/products/expensive")
    public List<Product> getExpensiveProducts(@RequestParam double minPrice) {
        return productService.findExpensiveProducts(minPrice);
    }

    @GetMapping("/products/page")
    public Page<Product> getProductsPage(Pageable pageable) {
        return productService.findAll(pageable);
    }

    // Product with Specification
    @GetMapping("/products/filter")
    public List<Product> filterProducts(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) Long categoryId,
                                        @RequestParam(required = false) Double minPrice,
                                        @RequestParam(required = false) Double maxPrice) {
        Specification<Product> spec = Specification.where(null);
        if (name != null) spec = spec.and(ProductSpecifications.hasName(name));
        if (categoryId != null) spec = spec.and(ProductSpecifications.hasCategory(categoryId));
        if (minPrice != null && maxPrice != null) spec = spec.and(ProductSpecifications.priceBetween(minPrice, maxPrice));
        return productService.findAll(spec);
    }

    // Category APIs
    @GetMapping("/categories")
    public List<Category> getAllCategories() { return categoryService.findAll(); }

    @GetMapping("/categories/root")
    public List<Category> getRootCategories() { return categoryService.findRootCategories(); }

    // Order APIs
    @GetMapping("/orders")
    public List<Order> getAllOrders() { return orderService.findAll(); }

    @GetMapping("/orders/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) { return orderService.findByUserId(userId); }

    // User APIs
    @GetMapping("/users")
    public List<User> getAllUsers() { return userService.findAll(); }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) { return userService.findById(id); }

    // Review APIs
    @GetMapping("/reviews")
    public List<Review> getAllReviews() { return reviewService.findAll(); }

    @GetMapping("/reviews/product/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable Long productId) { return reviewService.findByProductId(productId); }

    // OrderItem APIs
    @GetMapping("/order-items/order/{orderId}")
    public List<OrderItem> getOrderItemsByOrder(@PathVariable Long orderId) { return orderItemService.findByOrderId(orderId); }
}
