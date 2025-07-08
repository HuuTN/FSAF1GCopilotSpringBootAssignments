package com.example.demo.repository;

import com.example.demo.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, UserDTO> users = new HashMap<>();
    private long idCounter = 1;

    @Override
    public UserDTO save(UserDTO user) {
        if (user.getId() == null) {
            user.setId(idCounter++);
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<UserDTO> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        List<UserDTO> userList = new ArrayList<>(users.values());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userList.size());
        List<UserDTO> pageContent = start > end ? Collections.emptyList() : userList.subList(start, end);
        return new PageImpl<>(pageContent, pageable, userList.size());
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return users.containsKey(id);
    }
}
