package com.emirhan.todo.todo_api.service;

import java.util.Optional;

import com.emirhan.todo.todo_api.entity.User;

public interface UserService {
    User register(User user);
    Optional<User> findByUsername(String username);
}
