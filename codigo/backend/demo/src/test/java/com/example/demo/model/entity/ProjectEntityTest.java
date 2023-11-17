package com.example.demo.model.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectEntityTest {

    private ProjectEntity projectEntity;

    @BeforeEach
    public void setUp() {
        projectEntity = new ProjectEntity();
    }

    @Test
    public void testId() {
        projectEntity.setId(1L);
        assertEquals(1L, projectEntity.getId());
    }

    @Test
    public void testUser() {
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        projectEntity.setUser(user);
        assertEquals("testUser", projectEntity.getUser().getUsername());
    }

    @Test
    public void testProjectName() {
        projectEntity.setProjectName("TestProject");
        assertEquals("TestProject", projectEntity.getProjectName());
    }

    @Test
    public void testRpn() {
        projectEntity.setRpn("TestRPN");
        assertEquals("TestRPN", projectEntity.getRpn());
    }

    @Test
    public void testCompany() {
        projectEntity.setCompany("TestCompany");
        assertEquals("TestCompany", projectEntity.getCompany());
    }
}

