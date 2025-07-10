package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
} 