package com.example.assignment3.service.impl;

import com.example.assignment3.dto.UserDTO;
import com.example.assignment3.model.User;
import com.example.assignment3.repository.UserRepository;
import com.example.assignment3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user = userRepository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
            .map(user -> {
                UserDTO dto = new UserDTO();
                dto.setId(user.getId());
                dto.setName(user.getName());
                dto.setEmail(user.getEmail());
                return dto;
            });
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user = userRepository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
