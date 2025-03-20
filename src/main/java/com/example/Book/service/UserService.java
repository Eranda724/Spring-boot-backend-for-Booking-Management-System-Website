package com.example.Book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Book.model.Users;
import com.example.Book.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Method to register a new user
    public Users register(Users user) {
        // Validate that password and confirm password match
        if (!user.getPassword().equals(user.getConfirmpassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }

        // Encrypt the password
        user.setPassword(encoder.encode(user.getPassword()));

        // Clear the confirm password field (optional, but recommended)
        user.setConfirmpassword(null);

        // Save the user to the database
        return userRepo.save(user);
    }

    // Method to verify user credentials during login
    public String verify(Users user) {
        // Logic to authenticate the user (e.g., using AuthenticationManager)
        // For simplicity, we assume the user exists and return a success message
        return "Login successful for user: " + user.getUsername();
    }
}