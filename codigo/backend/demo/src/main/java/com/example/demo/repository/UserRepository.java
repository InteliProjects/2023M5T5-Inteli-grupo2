package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.UserEntity;

/**
 * Repository interface for managing user entities in the application.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Retrieves a UserDetails object based on the provided login (username).
     *
     * @param login The login (username) of the user.
     * @return A UserDetails object representing the user, or null if not found.
     */
    UserDetails findByLogin(String login);

    Optional<UserEntity> findById(Long id);

}