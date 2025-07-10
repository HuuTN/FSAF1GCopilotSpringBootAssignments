package com.example.demo.service;

import com.example.demo.model.UserDto;
// import java.util.List;
// imports removed

public interface UserService {
    UserDto createUser(UserDto userDto);
    org.springframework.data.domain.Page<UserDto> getAllUsers(org.springframework.data.domain.Pageable pageable);
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
