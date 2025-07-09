// A Spring Data JPA repository for the Product entity.
package main.java.com.example.repository;
import com.example.entity.Product;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Modify this query to support pagination and sorting.
    // A JPQL query to search for products by a keyword in the name and a maximum price.
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND p.price <= :maxPrice")
    List<Product> searchProducts(@Param("keyword") String keyword, @Param("maxPrice") BigDecimal maxPrice, Pageable pageable);

    // A native SQL query to count the number of products for a given category ID.
    @Query(value = "SELECT COUNT(*) FROM products WHERE category_id = :categoryId", nativeQuery = true)
    int countProductsByCategoryId(@Param("categoryId") Long categoryId);
}
