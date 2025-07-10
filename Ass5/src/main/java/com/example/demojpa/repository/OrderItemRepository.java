package com.example.demojpa.repository;

import com.example.demojpa.entity.OrderItem;
import com.example.demojpa.entity.Product;
import com.example.demojpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {
    // Custom query: Find all order items for a given product
    @Query("SELECT oi FROM OrderItem oi WHERE oi.product = :product")
    List<OrderItem> findByProduct(@Param("product") Product product);

    // Custom query: Find all order items for a given order
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order = :order")
    List<OrderItem> findByOrder(@Param("order") Order order);

    // Example JPQL custom query
    @Query("SELECT oi FROM OrderItem oi WHERE oi.price > :minPrice")
    List<OrderItem> findExpensiveItems(@Param("minPrice") double minPrice);
}
