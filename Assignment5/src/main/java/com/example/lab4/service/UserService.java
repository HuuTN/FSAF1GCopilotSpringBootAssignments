package com.example.lab4.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.lab4.model.dto.UserDTO;
import java.util.Optional;

public interface UserService {

    Page<UserDTO> getAllUsers(Pageable pageable);

    Optional<UserDTO> getUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    Optional<UserDTO> updateUser(Long id, UserDTO userDTO);

    boolean deleteUser(Long id);
} 
