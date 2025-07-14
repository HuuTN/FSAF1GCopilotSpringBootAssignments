package com.example.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab4.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods here if needed
}
