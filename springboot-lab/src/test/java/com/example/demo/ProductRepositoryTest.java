package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;


    @Test
    @DisplayName("searchProducts should return products containing keyword (integration test)")
    void searchProducts_shouldReturnMatchingProducts() {
        List<Product> products = productRepository.searchByName("phone");
        assertThat(products).isNotNull();
        assertThat(products).allMatch(p -> p.getName().toLowerCase().contains("phone"));
    }

    @Test
    @DisplayName("findExpensiveProducts should return products with price greater than minPrice")
    void findExpensiveProducts_shouldReturnExpensiveProducts() {
        List<Product> products = productRepository.findExpensiveProducts(1000.0);
        assertThat(products).isNotNull();
    }
}
