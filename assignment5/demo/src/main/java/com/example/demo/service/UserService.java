package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User createUser(UserDTO userDTO);
    Optional<User> updateUser(Long id, UserDTO userDTO);
    boolean deleteUser(Long id);
}
