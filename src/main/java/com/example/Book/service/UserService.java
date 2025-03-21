package com.example.Book.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Book.model.Consumer;
import com.example.Book.model.ServiceProvider;
import com.example.Book.repo.ConsumerRepository;
import com.example.Book.repo.ServiceProviderRepository;

@Service
public class UserService {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register Service Provider with password hashing
    public String registerServiceProvider(ServiceProvider provider) {
        Optional<ServiceProvider> exist = serviceProviderRepository.findByEmail(provider.getEmail());
        if (exist.isPresent()) {
            return "Service Provider already exists!";
        }
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));
        serviceProviderRepository.save(provider);
        return "Service Provider registered successfully!";
    }

    // Register Consumer with password hashing
    public String registerConsumer(Consumer consumer) {
        Optional<Consumer> exist = consumerRepository.findByEmail(consumer.getEmail());
        if (exist.isPresent()) {
            return "Consumer already exists!";
        }
        consumer.setPassword(passwordEncoder.encode(consumer.getPassword()));
        consumerRepository.save(consumer);
        return "Consumer registered successfully!";
    }

    // Login both - verify password and return JWT token
    public String loginUser(String email, String password) {
        Optional<Consumer> consumerOpt = consumerRepository.findByEmail(email);
        if (consumerOpt.isPresent()) {
            Consumer consumer = consumerOpt.get();
            if (passwordEncoder.matches(password, consumer.getPassword())) {
                return jwtService.generateToken(email);
            }
        }

        Optional<ServiceProvider> providerOpt = serviceProviderRepository.findByEmail(email);
        if (providerOpt.isPresent()) {
            ServiceProvider provider = providerOpt.get();
            if (passwordEncoder.matches(password, provider.getPassword())) {
                return jwtService.generateToken(email);
            }
        }

        throw new RuntimeException("Invalid email or password!");
    }

    // Optional: Role update stub if you want to extend role change logic
    public boolean updateUserRole(String role) {
        // Extend logic to update user role in DB if needed
        return true;
    }
}
