package com.alphaomegazed.aoz_apartments.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.alphaomegazed.aoz_apartments.repository_interfaces.UserRepository;
import com.alphaomegazed.aoz_apartments.model.UserModel;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // will implement a password encoder

    // Logic to look up users based on their username
    // Throws username exception if the username isnt found
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found."));

        return buildUserForAuthentication(user);
    }

    // Logic to convert the Application's user entity into a UserDetails object,
    // something I can work with
    private UserDetails buildUserForAuthentication(UserModel user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority());

    }

    // Logic to assign authority or 'priviledges' to the user
    // For the time being, I dont have different roles
    private List<GrantedAuthority> getAuthority() {

        // will modify if needed to apply different roles for each user
        return Collections.singletonList((new SimpleGrantedAuthority("ROLE_USER")));
    }
}
