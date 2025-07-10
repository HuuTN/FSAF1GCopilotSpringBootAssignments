// using @DataJpaTest
package com.example.usermanagement.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.usermanagement.entity.Product;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSearchProducts() {
        // Arrange
        String searchKeyword = "Laptop";

        // Act
        List<Product> products = productRepository.searchProducts(searchKeyword);

        // Assert
        assertThat(products).isNotEmpty();
        assertThat(products.get(0).getName()).contains(searchKeyword);
    }
}
