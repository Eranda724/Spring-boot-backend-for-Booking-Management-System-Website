package com.example.Book.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book.model.Consumer;
import com.example.Book.model.ServiceProvider;
import com.example.Book.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register Service Provider
    @PostMapping("/service-provider/register")
    public void registerProvider(@RequestBody ServiceProvider provider) {
        userService.registerServiceProvider(provider);
}

    // Register Consumer
    @PostMapping("/consumer/register")
    public void registerConsumer(@RequestBody Consumer consumer) {
        userService.registerConsumer(consumer);
}

    // Login for both Service Provider and Consumer
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        String token = userService.loginUser(email, password);
        return ResponseEntity.ok("Bearer " + token);
    }
}
