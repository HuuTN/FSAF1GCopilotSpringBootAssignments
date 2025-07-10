package com.example.repository;

import com.example.entity.Category;
import com.example.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private Category category;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        // Clear all existing data in correct order to handle foreign key constraints
        entityManager.getEntityManager().createQuery("DELETE FROM OrderItem").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM Order").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM Product").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM Category").executeUpdate();
        entityManager.flush();
        entityManager.clear();

        // Create test category
        category = new Category();
        category.setName("Electronics");
        category = entityManager.persistAndFlush(category);

        // Create test products
        product1 = new Product();
        product1.setName("Laptop Computer");
        product1.setPrice(new BigDecimal("999.99"));
        product1.setStock(10);
        product1.setCategory(category);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());
        product1 = entityManager.persistAndFlush(product1);

        product2 = new Product();
        product2.setName("Gaming Mouse");
        product2.setPrice(new BigDecimal("49.99"));
        product2.setStock(25);
        product2.setCategory(category);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());
        product2 = entityManager.persistAndFlush(product2);

        product3 = new Product();
        product3.setName("Wireless Keyboard");
        product3.setPrice(new BigDecimal("79.99"));
        product3.setStock(15);
        product3.setCategory(category);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());
        product3 = entityManager.persistAndFlush(product3);

        // Clear persistence context to ensure fresh data
        entityManager.clear();
    }

    @Test
    void testSearchProducts_WithKeywordAndMaxPrice_ShouldReturnMatchingProducts() {
        // Given
        String keyword = "mouse";
        BigDecimal maxPrice = new BigDecimal("100.00");
        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Product> result = productRepository.searchProducts(keyword, maxPrice, pageable);

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Gaming Mouse");
        assertThat(result.getContent().get(0).getPrice()).isLessThanOrEqualTo(maxPrice);
    }

    @Test
    void testSearchProducts_WithKeywordCaseInsensitive_ShouldReturnMatchingProducts() {
        // Given
        String keyword = "LAPTOP";
        BigDecimal maxPrice = new BigDecimal("1500.00");
        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Product> result = productRepository.searchProducts(keyword, maxPrice, pageable);

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).containsIgnoringCase("laptop");
    }

    @Test
    void testSearchProducts_WithPartialKeyword_ShouldReturnMatchingProducts() {
        // Given
        String keyword = "key";
        BigDecimal maxPrice = new BigDecimal("100.00");
        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Product> result = productRepository.searchProducts(keyword, maxPrice, pageable);

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).containsIgnoringCase("keyboard");
    }

    @Test
    void testSearchProducts_WithLowMaxPrice_ShouldFilterByPrice() {
        // Given
        String keyword = ""; // Empty keyword should match all products
        BigDecimal maxPrice = new BigDecimal("50.00");
        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Product> result = productRepository.searchProducts(keyword, maxPrice, pageable);

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Gaming Mouse");
        assertThat(result.getContent().get(0).getPrice()).isLessThanOrEqualTo(maxPrice);
    }

    @Test
    void testSearchProducts_WithHighMaxPrice_ShouldReturnAllMatchingProducts() {
        // Given
        String keyword = ""; // Empty keyword should match all products
        BigDecimal maxPrice = new BigDecimal("2000.00");
        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Product> result = productRepository.searchProducts(keyword, maxPrice, pageable);

        // Then
        assertThat(result.getContent()).hasSize(3);
        assertThat(result.getContent())
                .extracting(Product::getPrice)
                .allSatisfy(price -> assertThat(price).isLessThanOrEqualTo(maxPrice));
    }

    @Test
    void testSearchProducts_WithNoMatchingKeyword_ShouldReturnEmptyPage() {
        // Given
        String keyword = "nonexistent";
        BigDecimal maxPrice = new BigDecimal("1000.00");
        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Product> result = productRepository.searchProducts(keyword, maxPrice, pageable);

        // Then
        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isEqualTo(0);
    }

    @Test
    void testSearchProducts_WithPagination_ShouldReturnCorrectPage() {
        // Given
        String keyword = ""; // Empty keyword should match all products
        BigDecimal maxPrice = new BigDecimal("2000.00");
        Pageable pageable = PageRequest.of(0, 2); // First page with 2 items

        // When
        Page<Product> result = productRepository.searchProducts(keyword, maxPrice, pageable);

        // Then
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getTotalElements()).isEqualTo(3);
        assertThat(result.getTotalPages()).isEqualTo(2);
        assertThat(result.hasNext()).isTrue();
    }

    @Test
    void testSearchProducts_WithSecondPage_ShouldReturnRemainingItems() {
        // Given
        String keyword = ""; // Empty keyword should match all products
        BigDecimal maxPrice = new BigDecimal("2000.00");
        Pageable pageable = PageRequest.of(1, 2); // Second page with 2 items

        // When
        Page<Product> result = productRepository.searchProducts(keyword, maxPrice, pageable);

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getTotalElements()).isEqualTo(3);
        assertThat(result.isLast()).isTrue();
    }

    @Test
    void testSearchProducts_WithVeryLowMaxPrice_ShouldReturnEmptyPage() {
        // Given
        String keyword = ""; // Empty keyword should match all products
        BigDecimal maxPrice = new BigDecimal("10.00");
        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Product> result = productRepository.searchProducts(keyword, maxPrice, pageable);

        // Then
        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isEqualTo(0);
    }
}
