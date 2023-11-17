package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthenticationDTO;
import com.example.demo.dto.LoginResponseDTO;

import com.example.demo.dto.RegisterDTO;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * A controller class responsible for authentication and user registration.
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5174")
public class AuthController {

    /** The authentication manager for user login. */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** The repository for user data. */
    @Autowired
    private UserRepository userRepository;

    /** The service for generating authentication tokens. */
    @Autowired
    private TokenService tokenService;

    /**
     * Handles user login by authenticating the provided credentials and generating
     * an authentication token.
     *
     * @param authenticationDTO The authentication data containing the username and
     *                          password.
     * @return A ResponseEntity containing a LoginResponseDTO with the
     *         authentication token.
     */
    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(),
                authenticationDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = (UserEntity) auth.getPrincipal();

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setId(user.getId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Handles user registration by creating a new user account and storing it in
     * the repository.
     *
     * @param registerDTO The registration data containing user information.
     * @return A ResponseEntity indicating the success or failure of the
     *         registration process.
     */
    @PostMapping("/register")
    public ResponseEntity Register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (this.userRepository.findByLogin(registerDTO.getLogin()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        UserEntity newUser = new UserEntity(registerDTO.getName(), registerDTO.getSurname(), registerDTO.getLogin(),
                encryptedPassword, registerDTO.getRole());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
