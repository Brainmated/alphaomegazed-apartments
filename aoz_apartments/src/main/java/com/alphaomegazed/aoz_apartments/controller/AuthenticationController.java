package com.alphaomegazed.aoz_apartments.controller;

import org.springframework.web.bind.annotation.RestController;

import com.alphaomegazed.aoz_apartments.model.AuthenticationResponse;
import com.alphaomegazed.aoz_apartments.model.UserModel;
import com.alphaomegazed.aoz_apartments.service.AuthenticationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
#Overview
This REST Controller class is repsonsible for handling authentication requests.
It includes user registration and login.

#Standout Variables
'authService' being the service layer for the controller to perform authentication operations.
*/
@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    /*
     * #Takes a UserModel object containing user registration details.
     * #Returns a registration response with an authentication token.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserModel request) {
        AuthenticationResponse response = authService.register(request);
        return ResponseEntity.ok(response);

    }

    /*
     * #Returns an authentication token on a succesful login.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserModel request) {
        AuthenticationResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

}
