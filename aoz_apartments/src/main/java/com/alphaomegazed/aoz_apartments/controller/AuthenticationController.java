package com.alphaomegazed.aoz_apartments.controller;

import org.springframework.web.bind.annotation.RestController;

import com.alphaomegazed.aoz_apartments.model.AuthenticationResponse;
import com.alphaomegazed.aoz_apartments.model.UserModel;
import com.alphaomegazed.aoz_apartments.service.AuthenticationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserModel request) {

        return ResponseEntity.ok(authService.register(request));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserModel request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
