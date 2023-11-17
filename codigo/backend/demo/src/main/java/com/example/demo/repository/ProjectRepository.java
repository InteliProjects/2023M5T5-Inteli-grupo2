package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.ProjectEntity;

/**
 * Repository interface for managing project entities in the application.
 */
@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
        /**
        * Retrieves a list of project entities associated with a specific user ID.
        *
        * @param userId The user ID for which to retrieve associated projects.
        * @return A list of project entities associated with the specified user ID.
        */
        @Query("SELECT p FROM ProjectEntity p WHERE p.user.id = :user_id")
        List<ProjectEntity> getProjectsByUserId(@Param("user_id") Long userId);


        @Query("SELECT p FROM Projects p WHERE p.id_user = :id_user")
        List<ProjectEntity> findProjectByUserId(@Param("id_user") Long id_user);
        
} 