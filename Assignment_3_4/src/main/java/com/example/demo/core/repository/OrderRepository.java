package com.example.demo.core.repository;

import com.example.demo.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT o.* FROM orders o JOIN users u ON o.user_id = u.id WHERE u.id = :userId", nativeQuery = true)
    List<Order> findOrdersByUserIdNative(@Param("userId") Long userId);
}
