package com.alphaomegazed.aoz_apartments.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alphaomegazed.aoz_apartments.repository_interfaces.UserRepository;
import com.alphaomegazed.aoz_apartments.model.UserModel;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        if (authentication.getPrincipal() instanceof UserModel) {
            UserModel user = (UserModel) authentication.getPrincipal();

            // Check if user account is locked and unlock it
            if (!user.isAccountNonLocked()) {
                user.setAccountNonLocked(true);
                userRepository.save(user);
            }
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}