package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

// no unused imports

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // @Autowired removed
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User(null, userDto.getName(), userDto.getEmail());
        User saved = userRepository.save(user);
        return new UserDto(saved.getId(), saved.getName(), saved.getEmail());
    }

    @Override
    public org.springframework.data.domain.Page<UserDto> getAllUsers(org.springframework.data.domain.Pageable pageable) {
        org.springframework.data.domain.Page<User> userPage = userRepository.findAll(pageable);
        java.util.List<UserDto> userDtos = userPage.getContent().stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .collect(java.util.stream.Collectors.toList());
        return new org.springframework.data.domain.PageImpl<>(userDtos, pageable, userPage.getTotalElements());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("User with id " + id + " not found"));
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("User with id " + id + " not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        User updated = userRepository.save(user);
        return new UserDto(updated.getId(), updated.getName(), updated.getEmail());
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
