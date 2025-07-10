package com.example.lab3.service;

// Create service for User CRUD
// Add Pageable to GET all users

import com.example.lab3.dto.UserDTO;
import com.example.lab3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setId(null);
        return userRepository.save(userDTO);
    }

    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        if (!userRepository.existsById(id)) return Optional.empty();
        userDTO.setId(id);
        return Optional.of(userRepository.save(userDTO));
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }
}
