package com.example.demojpa.repository;

import com.example.demojpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    // Find by email
    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Optional<Customer> findByEmail(@Param("email") String email);

    // Custom query: Find customers with name like
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:name%")
    Optional<Customer> findByNameLike(@Param("name") String name);
}
