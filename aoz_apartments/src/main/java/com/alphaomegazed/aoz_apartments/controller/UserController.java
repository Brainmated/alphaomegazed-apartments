package main.java.com.alphaomegazed.aoz_apartments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.java.com.alphaomegazed.aoz_apartments.dto.UserCreationDTO;
import main.java.com.alphaomegazed.aoz_apartments.model.User;
import main.java.com.alphaomegazed.aoz_apartments.service.UserService;

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
    public ResponseEntity<User> createUser(@RequestBody UserCreationDTO userCreationDto) {

        try {
            User newUser = userService.createUser(userCreationDto.getUsername(), userCreationDto.getPassword());
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
