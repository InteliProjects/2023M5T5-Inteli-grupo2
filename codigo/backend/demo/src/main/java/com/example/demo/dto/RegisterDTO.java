package com.example.demo.dto;

import com.example.demo.model.entity.UserRole;

/**
 * Data Transfer Object (DTO) representing registration information for a user.
 */
public class RegisterDTO {

    /** The unique identifier for the user. */
    private Long id;

    /** The first name of the user. */
    private String name;

    /** The last name of the user. */
    private String surname;

    /** The login username of the user. */
    private String login;

    /** The password associated with the user's account. */
    private String password;

    /** The role (authorization level) of the user. */
    private UserRole role;

    /**
     * Default constructor for RegisterDTO.
     */
    public RegisterDTO(){

    } 

    /**
     * Gets the unique identifier for the user.
     *
     * @return The user's identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id The user's identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The user's first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of the user.
     *
     * @param name The user's first name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The user's last name.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the last name of the user.
     *
     * @param surname The user's last name to set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the login username of the user.
     *
     * @return The user's login username.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login username of the user.
     *
     * @param login The user's login username to set.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the password associated with the user's account.
     *
     * @return The user's account password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password associated with the user's account.
     *
     * @param password The user's account password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role (authorization level) of the user.
     *
     * @return The user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the role (authorization level) of the user.
     *
     * @param role The user's role to set.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }
   
}