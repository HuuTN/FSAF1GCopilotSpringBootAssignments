package com.example.copilot.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.copilot.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
