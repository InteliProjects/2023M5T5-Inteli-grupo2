package com.example.demo.dto;

/**
 * Data Transfer Object (DTO) representing a user.
 */
public class UserDTO {

    /** The unique identifier of the user. */
    private Long id;

    /** The first name of the user. */
    private String name;

    /** The last name of the user. */
    private String surname;

    /** The email address of the user. */
    private String email;

    /** The password associated with the user. */
    private String password;

    /**
     * Default constructor for UserDTO.
     */
    public UserDTO(){

    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return The unique identifier of the user.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of the user.
     *
     * @param name The first name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the last name of the user.
     *
     * @param surname The last name to set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password associated with the user.
     *
     * @return The password associated with the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password associated with the user.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
   
}