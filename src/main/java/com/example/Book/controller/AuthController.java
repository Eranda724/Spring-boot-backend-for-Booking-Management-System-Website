package com.example.Book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book.model.Consumer;
import com.example.Book.model.ServiceProvider;
import com.example.Book.model.UserSelect;
import com.example.Book.service.JWTService;
import com.example.Book.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    /**
     * Select User Role (e.g., Consumer or Service Provider)
     * @param userSelect User role selection object
     * @return ResponseEntity with role selection confirmation
     */
    @PostMapping("/select-role")
    public ResponseEntity<String> selectUserRole(@RequestBody UserSelect userSelect) {
        if ("CONSUMER".equals(userSelect.getRole()) || "SERVICE_PROVIDER".equals(userSelect.getRole())) {
            return ResponseEntity.ok("Role selected: " + userSelect.getRole());
        } else {
            return ResponseEntity.badRequest().body("Invalid user role");
        }
    }

    /**
     * Register a new user (either Consumer or Service Provider)
     * @param userSelect User role selection object
     * @return ResponseEntity with registration status
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserSelect userSelect) {
        if ("CONSUMER".equals(userSelect.getRole())) {
            Consumer consumer = new Consumer("Consumer", "consumer@example.com", "password");
            userService.registerConsumer(consumer);
            return ResponseEntity.ok("Consumer registered successfully!");
        } else if ("SERVICE_PROVIDER".equals(userSelect.getRole())) {
            ServiceProvider provider = new ServiceProvider("Provider", "provider@example.com", "password");
            userService.registerServiceProvider(provider);
            return ResponseEntity.ok("Service Provider registered successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid user role");
        }
    }

    /**
     * Login endpoint to generate JWT token
     * @param userSelect User role selection object
     * @return ResponseEntity with JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserSelect userSelect) {
        if ("CONSUMER".equals(userSelect.getRole())) {
            // Simulate consumer login
            String token = jwtService.generateToken("consumer@example.com");
            return ResponseEntity.ok(token);
        } else if ("SERVICE_PROVIDER".equals(userSelect.getRole())) {
            // Simulate service provider login
            String token = jwtService.generateToken("provider@example.com");
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Invalid user role");
        }
    }
}