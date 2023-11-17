package com.example.demo.model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRoleTest {

    @Test
    public void testAdminRole() {
        UserRole adminRole = UserRole.ADMIN;
        assertEquals("admin", adminRole.getRole());
    }

    @Test
    public void testUserRole() {
        UserRole userRole = UserRole.USER;
        assertEquals("user", userRole.getRole());
    }
}
