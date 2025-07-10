package com.example.lab4.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.lab4.entity.Category;
import com.example.lab4.entity.Product;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void whenSearchProducts_thenReturnMatchingProducts() {
        // Arrange
        Category category = new Category();
        category.setName("Electronics");
        categoryRepository.save(category);

        Product laptop = new Product();
        laptop.setName("Gaming Laptop");
        laptop.setPrice(1499.99);
        laptop.setCategory(category);
        productRepository.save(laptop);

        // Act
        Page<Product> results = productRepository.searchProducts("Laptop", 1500.00, PageRequest.of(0, 5));

        // Assert
        assertThat(results).isNotEmpty();
        assertThat(results.getContent().get(0).getName()).contains("Laptop");
    }
}
