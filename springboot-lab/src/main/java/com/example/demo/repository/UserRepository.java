package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find user by username
    User findByUsername(String username);

    // Find users by email containing keyword
    List<User> findByEmailContaining(String email);

    // Custom query: find users with more than N orders
    @Query("SELECT u FROM User u WHERE SIZE(u.orders) > ?1")
    List<User> findUsersWithMinOrders(int minOrders);
}
