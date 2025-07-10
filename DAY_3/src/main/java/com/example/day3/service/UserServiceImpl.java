
package com.example.day3.service;

import com.example.day3.dto.UserDto;
import com.example.day3.model.UserEntity;
import com.example.day3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        UserEntity entity = toEntity(userDto);
        UserEntity saved = userRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public UserDto updateUser(int index, UserDto userDto) {
        // In real app, use id instead of index
        if (!userRepository.existsById(userDto.getId())) {
            return null;
        }
        UserEntity entity = toEntity(userDto);
        UserEntity updated = userRepository.save(entity);
        return toDto(updated);
    }

    @Override
    public boolean deleteUser(int index) {
        // In real app, use id instead of index
        if (!userRepository.existsById((long) index)) {
            return false;
        }
        userRepository.deleteById((long) index);
        return true;
    }

    // Helper methods for mapping
    private UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    private UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return entity;
    }
     
    
}
