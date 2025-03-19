package com.example.Book.model;

import jakarta.persistence.*;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private boolean isActive;
    private String specialization;


    public Service(String name, String type, boolean isActive, String specialization) {
        this.name = name;
        this.type = type;
        this.isActive = isActive;
        this.specialization = specialization;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    @Override
    public String toString() {
        return "StudentController{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isActive=" + isActive +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
