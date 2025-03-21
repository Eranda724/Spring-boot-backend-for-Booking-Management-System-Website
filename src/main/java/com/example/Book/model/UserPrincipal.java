package com.example.Book.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
    private String email;
    private String password;
    private String role; // "SERVICE_PROVIDER" or "CONSUMER"

    // Constructor
    public UserPrincipal(ServiceProvider provider) {
        this.email = provider.getEmail();
        this.password = provider.getPassword();
        this.role = "SERVICE_PROVIDER";
    }

    public UserPrincipal(Consumer consumer) {
        this.email = consumer.getEmail();
        this.password = consumer.getPassword();
        this.role = "CONSUMER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // Using email as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
