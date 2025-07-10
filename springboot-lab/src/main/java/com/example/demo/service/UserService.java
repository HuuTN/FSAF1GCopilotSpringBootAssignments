package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    List<User> findByEmailContaining(String email);
    List<User> findUsersWithMinOrders(int minOrders);
    User save(User user);
}
