package com.example.demo.model.entity;

/**
 * Enumeration representing user roles in the application.
 */
public enum UserRole{
    /**
     * Represents the admin role.
     */
    ADMIN("admin"),

    /**
     * Represents the user role.
     */
    USER("user");

    private String role;

    /**
     * Constructs a UserRole enum value with the given role name.
     *
     * @param role The role name.
     */
    UserRole(String role){
        this.role= role;
    }

    /**
     * Get the role name associated with this enum value.
     *
     * @return The role name.
     */
    public String getRole(){
        return role;
    }
}