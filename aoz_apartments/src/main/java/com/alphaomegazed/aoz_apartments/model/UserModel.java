package com.alphaomegazed.aoz_apartments.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.springframework.context.annotation.Profile;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType; //included for identity strategy
import jakarta.persistence.GeneratedValue; //corrected import instead of javax.annotation

@Entity
@Table(name = "users") // create a table for a start
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Getters-------------------------------------------------
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters-------------------------------------------------
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
