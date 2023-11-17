package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a project entity in the application.
 */
@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;

    private String projectName;

    private String rpn;

    private String company;

    /**
     * Default constructor for ProjectEntity.
     */
    public ProjectEntity() {

    }

    /**
     * Get the project's ID.
     *
     * @return The project's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the project's ID.
     *
     * @param id The project's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the user associated with the project.
     *
     * @return The user associated with the project.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Set the user associated with the project.
     *
     * @param user The user associated with the project.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Get the project's name.
     *
     * @return The project's name.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Set the project's name.
     *
     * @param projectName The project's name.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Get the project's RPN (Risk Priority Number).
     *
     * @return The project's RPN.
     */
    public String getRpn() {
        return rpn;
    }

    /**
     * Set the project's RPN (Risk Priority Number).
     *
     * @param rpn The project's RPN.
     */
    public void setRpn(String rpn) {
        this.rpn = rpn;
    }

    /**
     * Get the company associated with the project.
     *
     * @return The company associated with the project.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the company associated with the project.
     *
     * @param company The company associated with the project.
     */
    public void setCompany(String company) {
        this.company = company;
    }
}