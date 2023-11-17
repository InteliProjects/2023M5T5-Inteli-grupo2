package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

/**
 * Service class responsible for authenticating users using Spring Security's UserDetailsService.
 */
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository repository;


    /**
     * Loads a user by their login (username) from the UserRepository.
     *
     * @param login The login (username) of the user to be loaded.
     * @return A UserDetails object representing the user.
     * @throws UsernameNotFoundException If the user with the specified login is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);

    }
}
