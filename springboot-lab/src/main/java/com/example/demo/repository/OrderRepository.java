package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Find orders by user id
    List<Order> findByUserId(Long userId);

    // Find orders placed after a certain date
    List<Order> findByOrderDateAfter(LocalDateTime date);

    // Custom query: find orders with more than N items
    @Query("SELECT o FROM Order o WHERE SIZE(o.items) > :minItems")
    List<Order> findOrdersWithMinItems(@Param("minItems") int minItems);
}
