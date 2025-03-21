package com.example.Book.model;

public class UserSelect {
    private String role; // "SERVICE_PROVIDER" or "CONSUMER"

    // Constructors
    public UserSelect() {}

    public UserSelect(String role) {
        this.role = role;
    }

    // Getters and Setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
