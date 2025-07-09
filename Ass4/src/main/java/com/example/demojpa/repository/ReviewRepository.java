package com.example.demojpa.repository;

import com.example.demojpa.entity.Review;
import com.example.demojpa.entity.Product;
import com.example.demojpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    @Query("SELECT r FROM Review r WHERE r.product = :product")
    List<Review> findByProduct(@Param("product") Product product);

    @Query("SELECT r FROM Review r WHERE r.customer = :customer")
    List<Review> findByCustomer(@Param("customer") Customer customer);
}
