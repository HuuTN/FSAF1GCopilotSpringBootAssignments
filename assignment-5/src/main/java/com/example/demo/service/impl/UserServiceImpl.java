package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // Implementation for adding a new user
    @Override
    public void addUser(User user) {
        // Logic to add user
        userRepository.save(user);

    }

    // Implementation for retrieving a user by ID
    @Override
    public User getUserById(Long userId) {
        // Logic to get user by ID
        return userRepository.findById(userId).orElse(null);
    }

    // Implementation for updating a user
    @Override
    public void updateUser(User user) {
        // Logic to update user
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + user.getId());
        }
    }

    // Implementation for deleting a user
    @Override
    public void deleteUser(Long userId) {
        // Logic to delete user
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::fromEntity) // Chuyển từng User sang UserDTO
                .collect(Collectors.toList());
    }

}
