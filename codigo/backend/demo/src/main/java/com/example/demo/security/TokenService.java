package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.model.entity.UserEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Service class responsible for generating and validating JWT (JSON Web Tokens) for user authentication.
 */
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Generates a JWT token for the given user entity.
     *
     * @param user The user entity for which the token is generated.
     * @return A JWT token as a string.
     * @throws RuntimeException If there is an error while generating the token.
     */
    public String generateToken(UserEntity user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    /**
     * Validates a JWT token and retrieves the subject (username) from it.
     *
     * @param token The JWT token to validate.
     * @return The subject (username) extracted from the token, or an empty string if validation fails.
     */
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    /**
     * Generates an expiration date for JWT tokens, typically set to 2 hours from the current time.
     *
     * @return An Instant representing the expiration date.
     */
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}