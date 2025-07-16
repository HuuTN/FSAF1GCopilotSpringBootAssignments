package com.example.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.entity.Order;
import com.example.model.enums.OrderStatus;

/**
 * Repository interface for Order entity operations.
 * Provides CRUD operations and custom query methods for order management.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find orders by user name.
     * 
     * @param userName the name of the user
     * @return list of orders for the specified user
     */
    List<Order> findByUser_Name(String userName);

    /**
     * Find orders by user ID.
     * 
     * @param userId the ID of the user
     * @return list of orders for the specified user
     */
    List<Order> findByUser_Id(Long userId);

    /**
     * Find orders by customer ID.
     * 
     * @param customerId the ID of the customer
     * @return list of orders for the specified customer
     */
    List<Order> findByCustomer_Id(Long customerId);

    /**
     * Find orders by status.
     * 
     * @param status the order status
     * @return list of orders with the specified status
     */
    List<Order> findByStatus(OrderStatus status);

    /**
     * Find orders by status with pagination.
     * 
     * @param status   the order status
     * @param pageable pagination information
     * @return page of orders with the specified status
     */
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);

    /**
     * Find orders by user ID and status.
     * 
     * @param userId the ID of the user
     * @param status the order status
     * @return list of orders matching the criteria
     */
    List<Order> findByUser_IdAndStatus(Long userId, OrderStatus status);

    /**
     * Find orders with total amount greater than specified value.
     * 
     * @param amount the minimum total amount
     * @return list of orders with total amount greater than the specified value
     */
    List<Order> findByTotalAmountGreaterThan(BigDecimal amount);

    /**
     * Find orders between date range.
     * 
     * @param startDate the start date
     * @param endDate   the end date
     * @return list of orders created between the specified dates
     */
    @Query("SELECT o FROM Order o WHERE o.createdAt BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    /**
     * Find orders by customer name.
     * 
     * @param customerName the name of the customer
     * @return list of orders for the specified customer
     */
    @Query("SELECT o FROM Order o WHERE o.customer.name = :customerName")
    List<Order> findByCustomerName(@Param("customerName") String customerName);

    /**
     * Find recent orders for a user (last 30 days).
     * 
     * @param userId the ID of the user
     * @return list of recent orders
     */
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.createdAt >= :thirtyDaysAgo ORDER BY o.createdAt DESC")
    List<Order> findRecentOrdersByUser(@Param("userId") Long userId,
            @Param("thirtyDaysAgo") LocalDateTime thirtyDaysAgo);

    /**
     * Calculate total revenue for a specific status.
     * 
     * @param status the order status
     * @return total revenue for orders with the specified status
     */
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.status = :status")
    BigDecimal calculateTotalRevenueByStatus(@Param("status") OrderStatus status);

    /**
     * Count orders by status.
     * 
     * @param status the order status
     * @return count of orders with the specified status
     */
    long countByStatus(OrderStatus status);

    /**
     * Count orders by user ID.
     * 
     * @param userId the ID of the user
     * @return count of orders for the specified user
     */
    long countByUser_Id(Long userId);

    /**
     * Find top orders by total amount.
     * 
     * @param pageable pagination information (use PageRequest.of(0, limit) for top
     *                 N)
     * @return page of orders ordered by total amount descending
     */
    @Query("SELECT o FROM Order o ORDER BY o.totalAmount DESC")
    Page<Order> findTopOrdersByAmount(Pageable pageable);

    /**
     * Check if user has any orders.
     * 
     * @param userId the ID of the user
     * @return true if user has orders, false otherwise
     */
    boolean existsByUser_Id(Long userId);

    /**
     * Find the most recent order for a user.
     * 
     * @param userId the ID of the user
     * @return the most recent order for the user
     */
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.createdAt DESC LIMIT 1")
    Optional<Order> findMostRecentOrderByUser(@Param("userId") Long userId);
}
