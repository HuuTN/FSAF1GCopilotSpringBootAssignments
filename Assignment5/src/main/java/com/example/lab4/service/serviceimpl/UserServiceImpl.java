package com.example.lab4.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.example.lab4.model.dto.UserDTO;
import com.example.lab4.model.entity.User;
import com.example.lab4.repository.UserRepository;
import com.example.lab4.service.UserService;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    private User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return user;
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return new PageImpl<>(
            page.getContent().stream().map(this::toDTO).collect(Collectors.toList()),
            pageable,
            page.getTotalElements()
        );
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::toDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setId(null);
        User user = toEntity(userDTO);
        User saved = userRepository.save(user);
        return toDTO(saved);
    }

    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        if (!userRepository.existsById(id)) return Optional.empty();
        userDTO.setId(id);
        User user = toEntity(userDTO);
        User saved = userRepository.save(user);
        return Optional.of(toDTO(saved));
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }
}
