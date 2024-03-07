package com.alphaomegazed.aoz_apartments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphaomegazed.aoz_apartments.repository_interfaces.UserRepository;
import com.alphaomegazed.aoz_apartments.model.UserModel;

//Logic for User management
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel createUser(String username, String password) {

        // Input validation for dupliactes
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("User " + username + " already exists.");
        }

        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);

        return userRepository.save(user);
    }
}