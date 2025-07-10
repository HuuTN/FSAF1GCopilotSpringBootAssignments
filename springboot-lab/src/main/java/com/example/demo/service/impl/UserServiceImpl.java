package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findByEmailContaining(String email) {
        return userRepository.findByEmailContaining(email);
    }

    @Override
    public List<User> findUsersWithMinOrders(int minOrders) {
        return userRepository.findUsersWithMinOrders(minOrders);
    }
}
