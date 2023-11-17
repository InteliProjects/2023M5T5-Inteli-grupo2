package com.example.demo.dto;

/**
 * A data transfer object (DTO) representing user authentication information.
 * It contains the user's login (username or email) and password.
 */
public class AuthenticationDTO {

    /** The user's login (username or email). */
    private String login;

    /** The user's password. */
    private String password;

    /**
     * Constructs a new AuthenticationDTO with default values.
     */
    public AuthenticationDTO(){

    }

    /**
     * Gets the user's login (username or email).
     *
     * @return The user's login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the user's login (username or email).
     *
     * @param login The user's login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}