package com.example.repository;

import com.example.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    // Integration test for the custom searchProducts method in ProductRepository.
    @Test
    void testSearchProducts() {
        // Save some test Product entities
        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setDescription("Gaming laptop");
        productRepository.save(p1);

        Product p2 = new Product();
        p2.setName("Phone");
        p2.setDescription("Smartphone");
        productRepository.save(p2);

        // Call the custom searchProducts method
        Page<Product> result = productRepository.searchProducts("Laptop", PageRequest.of(0, 10));

        // Assert that the returned Page contains the correct results
        assertEquals(1, result.getTotalElements());
        assertEquals("Laptop", result.getContent().get(0).getName());
    }
}
