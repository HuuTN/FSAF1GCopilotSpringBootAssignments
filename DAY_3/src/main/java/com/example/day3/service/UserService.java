package com.example.day3.service;

import com.example.day3.dto.UserDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto addUser(UserDto userDto);
    UserDto updateUser(int index, UserDto userDto);
    boolean deleteUser(int index);
}
