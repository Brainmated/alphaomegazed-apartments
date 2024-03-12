package com.alphaomegazed.aoz_apartments.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alphaomegazed.aoz_apartments.service.JwtService;
import com.alphaomegazed.aoz_apartments.service.UserDetailService;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
#Overview
This class extends OncePerRequestFilter to ensure that it is applied once per request.
It is used to authenticate users based on a JWT provided by the HTTP request header(bearer).

#Standout Variables
'jwtService' is a service layer that handles the JWT related functionality in extracting usernames and validating tokens.
'userDetailsService' loads user-specific data to fetch a username, a password and a role.
*/
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /*
     * #Executed once per HTTP request.
     * #Extracts the token for validation and sets the 'SecurityContextHolder'
     * authentication.
     * #If the token is invalid or non existant, then it continues to the filter
     * chain without authentication.
     * #filterChain represents the chain of filters that the request should go
     * through.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
        // throw new UnsupportedOperationException("Unimplemented method
        // 'doFilterInternal'");
    }

}
