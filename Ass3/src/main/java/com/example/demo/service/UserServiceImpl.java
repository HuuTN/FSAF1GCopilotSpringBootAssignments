package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create service for User CRUD
    @Override
    public UserDTO createUser(UserDTO user) {
        user.setId(null); // Ensure new user gets a new ID
        return userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Add Pageable to GET all users
    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        if (userRepository instanceof com.example.demo.repository.InMemoryUserRepository) {
            return ((com.example.demo.repository.InMemoryUserRepository) userRepository).findAll(pageable);
        }
        // fallback for other implementations
        List<UserDTO> all = userRepository.findAll();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), all.size());
        List<UserDTO> pageContent = start > end ? java.util.Collections.emptyList() : all.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, all.size());
    }
}
