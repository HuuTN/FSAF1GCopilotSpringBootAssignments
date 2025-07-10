package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

// Create service for User CRUD
public interface UserService {
    UserDTO createUser(UserDTO user);
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(Long id);
    UserDTO updateUser(Long id, UserDTO user);
    void deleteUser(Long id);
    // Add Pageable to GET all users
    Page<UserDTO> getAllUsers(Pageable pageable);
}
