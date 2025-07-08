package com.example.lab3.repository;

// In-memory repository for User CRUD
// Support Pageable for findAll

import com.example.lab3.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class UserRepository {
    private final Map<Long, UserDTO> users = new HashMap<>();
    private long idCounter = 1;

    public Page<UserDTO> findAll(Pageable pageable) {
        List<UserDTO> userList = new ArrayList<>(users.values());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userList.size());
        List<UserDTO> pageContent = userList.subList(start, end);
        return new PageImpl<>(pageContent, pageable, userList.size());
    }

    public Optional<UserDTO> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public UserDTO save(UserDTO user) {
        if (user.getId() == null) {
            user.setId(idCounter++);
        }
        users.put(user.getId(), user);
        return user;
    }

    public void deleteById(Long id) {
        users.remove(id);
    }

    public boolean existsById(Long id) {
        return users.containsKey(id);
    }
}
