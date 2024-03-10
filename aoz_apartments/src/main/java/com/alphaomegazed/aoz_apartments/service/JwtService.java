package com.alphaomegazed.aoz_apartments.service;

import java.sql.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import com.alphaomegazed.aoz_apartments.model.UserModel;

/*
#Overview
This service class provided the JWT-related operations for token generation, extraction of claims and token validation.
Interaction with 'UserModel' and 'UserDetails' is crucial to create and verify tokens based on user information.

#Standout Variables
'SECRET_KEY' is a SHA256 generated secret key to be used for signing and verifying HWT tokens.
*/
@Service
public class JwtService {

    private final String SECRET_KEY = "b493d48364afe44d11c0165cf470a4164d1e2609911ef998be868d46ade3de4e";

    /*
    #Extracts the username fro the JWT token.
    #Return the username as a string
    */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*
    #Validates the given JWT token against user details.
    #Checks of the token's username matches the 'UserDetails' username and whether the token is expired.
    #Return true if the token is valid.
    */
    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    /*
    #Checks if the token has expired based on the expiration claim.
    */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(0));
    }

    /*
    #Extracts the expiration date from the JWT token.
    #Returns the expiration date as a 'java.sql.Date' object.
    */
    private Date extractExpiration(String token) {
        return (Date) extractClaim(token, Claims::getExpiration);
    }

    /*
    #Extracts a claim from the token using the claims resolver function.
    #Returns the claim value
    */
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    /*
    #Extracts all claims from the JWT token after verifying with the signing key.
    #Return the 'Claims' object containing all token claims.
    */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /*
    #Generates a JWT token for the given 'UserModel'.
    #Sets username as subject, issues the current timestamp as the date and sets expiration 1 day after creation.
    #Signs the token with the signing key.
    #Returns the JWT token as a string.
    */
    public String generateToken(UserModel user) {
        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    /*
    #Decodes the 'SECRET_KEY' from Base64URL to a byte array and construct a 'SecretKey'.
    #Return the SecretKey.
    */
    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
