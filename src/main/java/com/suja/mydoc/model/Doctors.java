package com.suja.mydoc.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String first_name;
    public String last_name;
    public String address;
    public String phone;
    public  String email;
    public String website;
    public String image;
    public String description;
    private final LocalDateTime created_at = LocalDateTime.now();
    public LocalDateTime updated_at = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;



    public Integer getId() {
        return id;
    }


    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public Specialization getSpecialization() {
        return specialization;
    }
}
