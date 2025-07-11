package com.example.demo.repository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void searchByNameAndMaxPrice_shouldReturnMatchingProducts() {
        // Arrange
        Product p1 = new Product();
        p1.setName("Apple iPhone");
        p1.setPrice(Double.valueOf(900));
        p1.setStock(Integer.valueOf(10));
        productRepository.save(p1);

        Product p2 = new Product();
        p2.setName("Samsung Galaxy");
        p2.setPrice(Double.valueOf(800));
        p2.setStock(Integer.valueOf(15));
        productRepository.save(p2);

        Product p3 = new Product();
        p3.setName("Apple Watch");
        p3.setPrice(Double.valueOf(400));
        p3.setStock(Integer.valueOf(20));
        productRepository.save(p3);

        // Act
        List<Product> results = productRepository.searchByNameAndMaxPrice("apple", 1000.0);

        // Assert
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(p -> p.getName().equals("")));
        assertTrue(results.stream().anyMatch(p -> p.getName().equals("Apple Watch")));
    }
}
