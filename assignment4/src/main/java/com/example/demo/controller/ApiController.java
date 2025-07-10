package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/all")
public class ApiController {
    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderItemRepository orderItemRepository;
    @Autowired private ReviewRepository reviewRepository;

    // --- User Endpoints ---
    @GetMapping("/users")
    public List<User> getAllUsers() { return userRepository.findAll(); }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user) { return userRepository.save(user); }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) { userRepository.deleteById(id); return ResponseEntity.noContent().build(); }
        else { return ResponseEntity.notFound().build(); }
    }

    // --- Product Endpoints ---
    @GetMapping("/products")
    public List<Product> getAllProducts() { return productRepository.findAll(); }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) { return productRepository.save(product); }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) { productRepository.deleteById(id); return ResponseEntity.noContent().build(); }
        else { return ResponseEntity.notFound().build(); }
    }

    // --- Category Endpoints ---
    @GetMapping("/categories")
    public List<Category> getAllCategories() { return categoryRepository.findAll(); }
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) { return categoryRepository.save(category); }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryRepository.existsById(id)) { categoryRepository.deleteById(id); return ResponseEntity.noContent().build(); }
        else { return ResponseEntity.notFound().build(); }
    }

    // --- Order Endpoints ---
    @GetMapping("/orders")
    public List<Order> getAllOrders() { return orderRepository.findAll(); }
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) { return orderRepository.save(order); }
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) { orderRepository.deleteById(id); return ResponseEntity.noContent().build(); }
        else { return ResponseEntity.notFound().build(); }
    }

    // --- OrderItem Endpoints ---
    @GetMapping("/order-items")
    public List<OrderItem> getAllOrderItems() { return orderItemRepository.findAll(); }
    @GetMapping("/order-items/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        return orderItemRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/order-items")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) { return orderItemRepository.save(orderItem); }
    @DeleteMapping("/order-items/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        if (orderItemRepository.existsById(id)) { orderItemRepository.deleteById(id); return ResponseEntity.noContent().build(); }
        else { return ResponseEntity.notFound().build(); }
    }

    // --- Review Endpoints ---
    @GetMapping("/reviews")
    public List<Review> getAllReviews() { return reviewRepository.findAll(); }
    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/reviews")
    public Review createReview(@RequestBody Review review) { return reviewRepository.save(review); }
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (reviewRepository.existsById(id)) { reviewRepository.deleteById(id); return ResponseEntity.noContent().build(); }
        else { return ResponseEntity.notFound().build(); }
    }
}
