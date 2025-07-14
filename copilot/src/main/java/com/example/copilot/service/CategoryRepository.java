package com.example.copilot.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.copilot.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
