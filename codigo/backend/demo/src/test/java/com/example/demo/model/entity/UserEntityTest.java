package com.example.demo.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity("Arthur", "Guilherme", "Arthur.Guilherme", "password", UserRole.USER);
    }

    @Test
    public void testGetId() {
        assertNull(userEntity.getId());
        userEntity.setId(1L);
        assertEquals(1L, userEntity.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Arthur", userEntity.getName());
        userEntity.setName("Jane");
        assertEquals("Jane", userEntity.getName());
    }

    @Test
    public void testGetSurname() {
        assertEquals("Guilherme", userEntity.getSurname());
        userEntity.setSurname("Smith");
        assertEquals("Smith", userEntity.getSurname());
    }

    @Test
    public void testGetLogin() {
        assertEquals("Arthur.Guilherme", userEntity.getLogin());
        userEntity.setLogin("jane.smith");
        assertEquals("jane.smith", userEntity.getLogin());
    }

    @Test
    public void testGetRole() {
        assertEquals(UserRole.USER, userEntity.getRole());
        userEntity.setRole(UserRole.ADMIN);
        assertEquals(UserRole.ADMIN, userEntity.getRole());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", userEntity.getPassword());
        userEntity.setPassword("newPassword");
        assertEquals("newPassword", userEntity.getPassword());
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userEntity.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        userEntity.setRole(UserRole.ADMIN);
        authorities = userEntity.getAuthorities();
        assertEquals(2, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    public void testGetUsername() {
        assertEquals("Arthur.Guilherme", userEntity.getUsername());
        userEntity.setUsername("jane.smith");
        assertEquals("jane.smith", userEntity.getUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(userEntity.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(userEntity.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(userEntity.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(userEntity.isEnabled());
    }
}
