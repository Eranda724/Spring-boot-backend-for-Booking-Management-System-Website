package com.example.Book.controller;

import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book.model.Service;
import com.example.Book.repo.ServiceRepo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ServiceController {

    private final ServiceRepo serviceRepository;

    public ServiceController(ServiceRepo serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/service")
    public List<Service> getServices() {

        return serviceRepository.findAll();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");

    }

    @PostMapping("/service")
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }

}
