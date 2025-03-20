package com.example.Book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book.model.Users;
import com.example.Book.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register/consumer")
    public ResponseEntity<?> registerConsumer(@RequestBody Users user) {
        user.setRole("CONSUMER");
        return ResponseEntity.ok(service.register(user));
    }
    
    @PostMapping("/register/provider")
    public ResponseEntity<?> registerProvider(@RequestBody Users user) {
        user.setRole("SERVICE_PROVIDER");
        return ResponseEntity.ok(service.register(user));
    }

    @PostMapping("/login/consumer")
    public String loginConsumer(@RequestBody Users user) {
        user.setRole("CONSUMER");
        return service.verify(user);
    }
    
    @PostMapping("/login/provider")
    public String loginProvider(@RequestBody Users user) {
        user.setRole("SERVICE_PROVIDER");
        return service.verify(user);
    }
}
