package com.example.demo.model.entity;

import java.util.Collection;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Represents a user entity in the application.
 */
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private UserRole role;

    /**
     * Default constructor for UserEntity.
     */
    public UserEntity() {

    }

    /**
     * Constructor to create a UserEntity with specified attributes.
     *
     * @param name     The user's name.
     * @param surname  The user's surname.
     * @param login    The user's login.
     * @param password The user's password.
     * @param role     The user's role (ADMIN or USER).
     */
    public UserEntity(String name, String surname, String login, String password, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Get the user's ID.
     *
     * @return The user's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the user's ID.
     *
     * @param id The user's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the user's name.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user's name.
     *
     * @param name The user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user's surname.
     *
     * @return The user's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the user's surname.
     *
     * @param surname The user's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }



    /**
     * Set the user's login.
     *
     * @param login The user's login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the user's role.
     *
     * @return The user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Set the user's role.
     *
     * @param role The user's role.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Get the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the user's authorities (roles).
     *
     * @return A collection of user's authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Get the user's username (login).
     *
     * @return The user's username (login).
     */
    @Override
    public String getUsername() {
        return login;
    }

    /**
     * Set the user's username (login).
     *
     * @param username The user's username (login) to set.
     */
    public void setUsername(String username) {
        this.login = username;
    }



    /**
     * Check if the user's account is non-expired.
     *
     * @return True if the user's account is non-expired, false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Check if the user's account is non-locked.
     *
     * @return True if the user's account is non-locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Check if the user's credentials are non-expired.
     *
     * @return True if the user's credentials are non-expired, false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Check if the user's account is enabled.
     *
     * @return True if the user's account is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getLogin() {
        return login;
    }

}