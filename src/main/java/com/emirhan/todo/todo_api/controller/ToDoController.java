package com.emirhan.todo.todo_api.controller;

import java.util.List;


import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import com.emirhan.todo.todo_api.entity.ToDo;
import com.emirhan.todo.todo_api.entity.User;
import com.emirhan.todo.todo_api.service.ToDoService;
import com.emirhan.todo.todo_api.service.UserService;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    private final UserService userService;
    private final ToDoService toDoService;

    public ToDoController(UserService userService, ToDoService toDoService) {
        this.userService = userService;
        this.toDoService = toDoService;
    }
    
    
    @GetMapping("/test")
    public String testJWT(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return "JWT çalışıyor, user: " + user.getUsername();
    }

    
    @GetMapping
    public List<ToDo> getMyTodos(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toDoService.getUserTodos(user.getId());
    }

    
    @PostMapping
    public ToDo createToDo(@RequestBody ToDo toDo, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        toDo.setUser(user);
        return toDoService.create(toDo);
    }
}
