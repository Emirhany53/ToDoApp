package com.emirhan.todo.todo_api.controller;

import com.emirhan.todo.todo_api.entity.User;
import com.emirhan.todo.todo_api.service.UserService;
import com.emirhan.todo.todo_api.config.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        Optional<User> optionalUser = userService.findByUsername(user.getUsername());
        User dbUser = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtUtil.generateToken(dbUser.getUsername());
        return new LoginResponse(dbUser.getUsername(), token);
    }

    public static class LoginResponse {
        private String username;
        private String token;

        public LoginResponse(String username, String token) {
            this.username = username;
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public String getToken() {
            return token;
        }
    }
}
