package com.alphaomegazed.aoz_apartments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alphaomegazed.aoz_apartments.filter.JwtAuthenticationFilter;
import com.alphaomegazed.aoz_apartments.service.UserDetailService;

/*
#Overview
SecurityConfig is a configuration class that defines the security filter chain.
It encodes the password to be used and it defines the authentication manager bean

#Standout Variables
'userDetailsService' is an implementation of Spring Security's UserDetailService to load user-specific data
'jwtAuthentication' is a custom filter for the JWT token authentication
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /*
    #This method defines the chain that applies to HTTP requests.
    #Disables csrf protection since the authentication is stateless.
    #Configures requests and allows public access to /register and /login.
    #Sets jwtAuthenticationFilter before UsernamePassowrdAuthenticationFilter to intercept and process JWT tokens. 
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/login/**", "/register**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    //This method returns a BCryptPasswordEncoder instance to be used for hashing passwords.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //This method retrieves and returns AuthenticationManager from Spring Security.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
