package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.UserDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import java.util.List;
// imports removed

public interface UserService {
    UserDto createUser(UserDto userDto);
    Page<UserDto> getAllUsers(Pageable pageable);
    Optional<UserDto> getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
