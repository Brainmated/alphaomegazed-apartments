package main.java.com.alphaomegazed.aoz_apartments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import main.java.com.alphaomegazed.aoz_apartments.service.UserService;

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public User createUser(@RequestParam String username, @RequestParam String password) {

        return userService.createUser(username, password);
    }
}
