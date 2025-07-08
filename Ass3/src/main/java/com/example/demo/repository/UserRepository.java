package com.example.demo.repository;

import com.example.demo.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface UserRepository {
    UserDTO save(UserDTO user);
    Optional<UserDTO> findById(Long id);
    List<UserDTO> findAll();
    Page<UserDTO> findAll(Pageable pageable);
    void deleteById(Long id);
    boolean existsById(Long id);
}
