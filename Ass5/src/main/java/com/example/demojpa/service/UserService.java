package com.example.demojpa.service;

import com.example.demojpa.entity.User;
import com.example.demojpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    void deleteUser(Long id);
    User updateUser(Long id, User userDetails);
    User findByUsername(String username);
    User findByEmail(String email);
}