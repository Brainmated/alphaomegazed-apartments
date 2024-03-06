package main.java.com.alphaomegazed.aoz_apartments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.alphaomegazed.aoz_apartments.repository_interfaces.UserRepository;
import main.java.com.alphaomegazed.aoz_apartments.model.User;

//Logic for User management
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password) {

        // Input validation for dupliactes
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("User " + username + " already exists.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return userRepository.save(user);
    }
}