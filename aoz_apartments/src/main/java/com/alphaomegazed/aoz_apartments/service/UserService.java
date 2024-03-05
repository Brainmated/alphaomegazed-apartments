package main.java.com.alphaomegazed.aoz_apartments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.alphaomegazed.aoz_apartments.repository_interfaces.UserRepository;
import main.java.com.alphaomegazed.aoz_apartments.model.User;

/* User Management Class */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return userRepository.save(user);
    }
}