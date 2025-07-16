package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserService {
    void addUser(User user);

    User getUserById(Long userId);

    void updateUser(User user);

    void deleteUser(Long userId);

    List<UserDTO> getAllUsers();
}
