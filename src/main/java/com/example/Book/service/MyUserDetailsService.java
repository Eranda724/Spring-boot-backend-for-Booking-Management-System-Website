package com.example.Book.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Book.model.Consumer;
import com.example.Book.model.ServiceProvider;
import com.example.Book.model.UserPrincipal;
import com.example.Book.repo.ConsumerRepository;
import com.example.Book.repo.ServiceProviderRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // First, check if the user exists in the Consumers table
        Optional<Consumer> consumer = consumerRepository.findByEmail(email);
        if (consumer.isPresent()) {
            return new UserPrincipal(consumer.get()); // Assuming you have UserPrincipal
        }

        // If not found in Consumers, check the Service Providers table
        Optional<ServiceProvider> provider = serviceProviderRepository.findByEmail(email);
        if (provider.isPresent()) {
            return new UserPrincipal(provider.get());
        }

        // If not found in either table, throw an error
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
