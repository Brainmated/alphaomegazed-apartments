package com.alphaomegazed.aoz_apartments.dto;

import org.springframework.context.annotation.Profile;

public class UserCreationDto {
    private String username;
    private String password;

    // Setters-----------------------------------------
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters-----------------------------------------
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
