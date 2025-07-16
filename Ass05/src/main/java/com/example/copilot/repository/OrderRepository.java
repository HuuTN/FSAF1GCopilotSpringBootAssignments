package com.example.copilot.repository;

import com.example.copilot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    List<Order> findByUserId(Long userId);
    @Query("SELECT o FROM Order o JOIN o.orderItems oi WHERE oi.product.id = :productId")
    List<Order> findByProductId(Long productId);
}
