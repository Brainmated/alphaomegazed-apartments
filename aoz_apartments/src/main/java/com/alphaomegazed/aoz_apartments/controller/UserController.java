package main.java.com.alphaomegazed.aoz_apartments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import main.java.com.alphaomegazed.aoz_apartments.model.User;
import main.java.com.alphaomegazed.aoz_apartments.service.UserService;

@RestController // this is class level
public class UserController {

    @Autowired
    private UserService userService;

    // crucial for REST Api's to produce and consume JSON
    @PostMapping(value = "/createUser", consumes = "application/json", produces = "application/json")
    public User createUser(@RequestParam String username, @RequestParam String password) {

        return userService.createUser(username, password);
    }
}
