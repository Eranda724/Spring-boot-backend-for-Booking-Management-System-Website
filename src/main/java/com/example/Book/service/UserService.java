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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerServiceProvider(ServiceProvider provider) {
        provider.setPassword(passwordEncoder.encode(provider.getPassword())); // Hash password
        serviceProviderRepository.save(provider);
    }

    public void registerConsumer(Consumer consumer) {
        consumer.setPassword(passwordEncoder.encode(consumer.getPassword())); // Hash password
        consumerRepository.save(consumer);
    }

    public Optional<ServiceProvider> findServiceProviderByEmail(String email) {
        return serviceProviderRepository.findByEmail(email);
    }

    public Optional<Consumer> findConsumerByEmail(String email) {
        return consumerRepository.findByEmail(email);
    }
}
