package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        List<User> users = userService.getAllUsers(page, size);
        List<UserDTO> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(new UserDTO(user.getId(), user.getName(), user.getEmail()));
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
                .orElse(null);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        User user = new User(null, userDTO.getName(), userDTO.getEmail(), null);
        User saved = userService.createUser(user);
        return new UserDTO(saved.getId(), saved.getName(), saved.getEmail());
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        User user = new User(id, userDTO.getName(), userDTO.getEmail(), null);
        User updated = userService.updateUser(id, user);
        return new UserDTO(updated.getId(), updated.getName(), updated.getEmail());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
