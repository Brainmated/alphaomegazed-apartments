package com.alphaomegazed.aoz_apartments.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alphaomegazed.aoz_apartments.model.AuthenticationResponse;
import com.alphaomegazed.aoz_apartments.model.UserModel;
import com.alphaomegazed.aoz_apartments.repository_interfaces.UserRepository;

/*
#Overview
This service class provided methods for registering new users and authenticating existing ones.
It users UserRepository for persistence and AuthenticationManager for user Authentication.

#Standout variables
'userRepository' is the repository interface to handle CRUD operations for the 'UserModel' entity.
'passwordEncoder' is the component that encodes plain text passwords.
'authenticationManager' is the Spring Security interface for authenticating 'Authentication' objects.
*/
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /*
     * #Registers a new user with the provided user details.
     * #Encodes the password using PasswordEncoder.
     * #Persists the new user object using the UserRepository interface.
     * #Generates a JWT token for the newly registered user using 'JwtService'.
     * #Return the JWT token through the AuthenticationResponse.
     */
    public AuthenticationResponse register(UserModel request) {
        UserModel user = new UserModel();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        user = userRepository.save(user);

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse("User id has been registered.", token);
    }

    /*
     * #Authenticates the user using AuthenticationManager with the provided
     * username and password.
     * #Fetches the usernames from the UserRepository.
     * #Throws exception if the user cant be found.
     * #Returns the JWT token.
     */
    public AuthenticationResponse authenticate(UserModel request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        UserModel user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse("Successful login for user id.", token);
    }
}
