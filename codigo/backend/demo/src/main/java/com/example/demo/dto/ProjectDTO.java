package com.example.demo.dto;

/**
 * Data Transfer Object (DTO) representing project information.
 */
public class ProjectDTO {
    /** The unique identifier for the project. */
    private Long id;

    /** The user ID associated with the project. */
    private Long userId;

    /** The name of the project. */
    private String projectName;

    /** The relative path name (RPN) associated with the project. */
    private String rpn;

    /** The company name associated with the project. */
    private String company;

    /**
     * Default constructor for ProjectDTO.
     */
    public ProjectDTO(){

    }

    /**
     * Gets the unique identifier for the project.
     *
     * @return The project's identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the project.
     *
     * @param id The project's identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user ID associated with the project.
     *
     * @return The user ID associated with the project.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the project.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the name of the project.
     *
     * @return The project name.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the name of the project.
     *
     * @param projectName The project name to set.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Gets the relative path name (RPN) associated with the project.
     *
     * @return The RPN associated with the project.
     */
    public String getRpn() {
        return rpn;
    }

    /**
     * Sets the relative path name (RPN) associated with the project.
     *
     * @param rpn The RPN to set.
     */
    public void setRpn(String rpn) {
        this.rpn = rpn;
    }

    /**
     * Gets the company name associated with the project.
     *
     * @return The company name associated with the project.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company name associated with the project.
     *
     * @param company The company name to set.
     */
    public void setCompany(String company) {
        this.company = company;
    }

}