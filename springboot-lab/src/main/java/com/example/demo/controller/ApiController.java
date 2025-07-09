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
    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    // Product APIs
    @GetMapping("/products")
    public List<Product> getAllProducts() { return productRepository.findAll(); }

    @GetMapping("/products/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productRepository.searchByName(keyword);
    }

    @GetMapping("/products/expensive")
    public List<Product> getExpensiveProducts(@RequestParam double minPrice) {
        return productRepository.findExpensiveProducts(minPrice);
    }

    @GetMapping("/products/page")
    public Page<Product> getProductsPage(Pageable pageable) {
        return productRepository.findAll(pageable);
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
        return productRepository.findAll(spec);
    }

    // Category APIs
    @GetMapping("/categories")
    public List<Category> getAllCategories() { return categoryRepository.findAll(); }

    @GetMapping("/categories/root")
    public List<Category> getRootCategories() { return categoryRepository.findRootCategories(); }

    // Order APIs
    @GetMapping("/orders")
    public List<Order> getAllOrders() { return orderRepository.findAll(); }

    @GetMapping("/orders/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) { return orderRepository.findByUserId(userId); }

    // User APIs
    @GetMapping("/users")
    public List<User> getAllUsers() { return userRepository.findAll(); }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) { return userRepository.findById(id).orElse(null); }

    // Review APIs
    @GetMapping("/reviews")
    public List<Review> getAllReviews() { return reviewRepository.findAll(); }

    @GetMapping("/reviews/product/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable Long productId) { return reviewRepository.findByProductId(productId); }

    // OrderItem APIs
    @GetMapping("/order-items/order/{orderId}")
    public List<OrderItem> getOrderItemsByOrder(@PathVariable Long orderId) { return orderItemRepository.findByOrderId(orderId); }
}
