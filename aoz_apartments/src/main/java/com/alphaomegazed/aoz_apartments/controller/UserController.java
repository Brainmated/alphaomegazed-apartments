package com.alphaomegazed.aoz_apartments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alphaomegazed.aoz_apartments.dto.UserCreationDto;
import com.alphaomegazed.aoz_apartments.model.UserModel;
import com.alphaomegazed.aoz_apartments.service.UserService;

/* The createUser method will accept a data transfer object.
 * In contrast to the @RequestParam, the DTO approach allows
 * for more appropriate calls for JSON POST requests.
 */
@RestController // this is class level
public class UserController {

    @Autowired
    private UserService userService;

    // crucial for REST Api's to produce and consume JSON
    @PostMapping(value = "/createUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> createUser(@RequestBody UserCreationDto userCreationDto) {

        try {
            UserModel newUser = userService.createUser(userCreationDto.getUsername(), userCreationDto.getPassword());
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
