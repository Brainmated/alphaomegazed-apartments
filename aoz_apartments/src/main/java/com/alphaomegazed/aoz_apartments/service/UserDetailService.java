package com.alphaomegazed.aoz_apartments.service;

import com.alphaomegazed.aoz_apartments.model.UserModel;
import com.alphaomegazed.aoz_apartments.repository_interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
#Overview
This service class implements the 'UserDetailsService' interface from Spring Security.
It's responsible for retrieving the user details from the data persistence layer.

#Standout Variables
'userRepository' is an instance of the UserRepository interface which is used to perform CRUD operations on the 'UserModel' entity.
*/
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    #Retrieves the user by their username using the userRepository interface.
    #Returns the object containing the user's information if the user is found.
    #Throws exception if the user is not found.
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
