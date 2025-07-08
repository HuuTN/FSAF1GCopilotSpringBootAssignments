package com.example.assignment3.service;

import com.example.assignment3.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Page<UserDTO> getAllUsers(Pageable pageable);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO getUserById(Long id);
}
