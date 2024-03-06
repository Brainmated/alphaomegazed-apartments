package main.java.com.alphaomegazed.aoz_apartments.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import main.java.com.alphaomegazed.aoz_apartments.repository_interfaces.UserRepository;
import main.java.com.alphaomegazed.aoz_apartments.model.User;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Logic to look up users based on their username
    // Throws username exception if the username isnt found
    @Override
    public UserDetails findUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found."));

        return buildUserForAuthentication(user);
    }

    // Logic to convert the Application's user entity into a UserDetails object,
    // something I can work with
    private UserDetails buildUserForAuthentication(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority(user));

    }

    // Logic to assign authority or 'priviledges' to the user
    // For the time being, I dont have different roles
    private List<GrantedAuthority> getAuthority(User user) {

        // will modify if needed to apply different roles for each user
        return Collections.singletonList((new SimpleGrantedAuthority("ROLE_USER")));
    }
}
