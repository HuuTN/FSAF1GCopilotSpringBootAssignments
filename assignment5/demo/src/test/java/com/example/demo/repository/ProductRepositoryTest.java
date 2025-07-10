package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndFindById() {
        Product product = Product.builder().name("Test Product").price(10.0).stock(5).build();
        Product saved = productRepository.save(product);
        Optional<Product> found = productRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test Product");
    }

    @Test
    void testFindAll() {
        Product product1 = Product.builder().name("Product 1").price(5.0).stock(2).build();
        Product product2 = Product.builder().name("Product 2").price(15.0).stock(8).build();
        productRepository.save(product1);
        productRepository.save(product2);
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void testSearchProductsByNamePaged() {
        // Arrange
        Product product1 = Product.builder().name("Apple iPhone").price(999.0).stock(10).build();
        Product product2 = Product.builder().name("Samsung Galaxy").price(899.0).stock(8).build();
        Product product3 = Product.builder().name("Apple Watch").price(399.0).stock(5).build();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = productRepository.searchProducts("Apple", pageable);

        assertThat(page.getContent()).extracting(Product::getName)
            .containsExactlyInAnyOrder("Apple iPhone", "Apple Watch");
        assertThat(page.getTotalElements()).isEqualTo(2);
    }
}
