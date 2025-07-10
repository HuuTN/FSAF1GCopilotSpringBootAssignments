package com.example.demo.service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAllReturnsAllUsers() {
        User user1 = User.builder().name("Alice").email("alice@example.com").password("pass").build();
        User user2 = User.builder().name("Bob").email("bob@example.com").password("pass2").build();
        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getName).containsExactlyInAnyOrder("Alice", "Bob");
    }
}
