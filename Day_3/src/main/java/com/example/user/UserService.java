package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers(int page, int size) {
        List<User> allUsers = userRepository.findAll();
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, allUsers.size());
        if (fromIndex > allUsers.size()) {
            return Collections.emptyList();
        }
        return allUsers.subList(fromIndex, toIndex);
    }
}
