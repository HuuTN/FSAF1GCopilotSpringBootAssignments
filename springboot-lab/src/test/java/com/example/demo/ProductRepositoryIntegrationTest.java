package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(scripts = "/data.sql") // Ensure test data is loaded
class ProductRepositoryIntegrationTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("searchByName should return products containing keyword")
    void searchByName_shouldReturnMatchingProducts() {
        List<Product> products = productRepository.searchByName("phone");
        assertThat(products).isNotEmpty();
        assertThat(products.stream().anyMatch(p -> p.getName().toLowerCase().contains("phone"))).isTrue();
    }

    @Test
    @DisplayName("findExpensiveProducts should return products with price greater than minPrice")
    void findExpensiveProducts_shouldReturnExpensiveProducts() {
        List<Product> products = productRepository.findExpensiveProducts(1000.0);
        assertThat(products).isNotEmpty();
        assertThat(products.stream().allMatch(p -> p.getPrice() > 1000.0)).isTrue();
    }
}
