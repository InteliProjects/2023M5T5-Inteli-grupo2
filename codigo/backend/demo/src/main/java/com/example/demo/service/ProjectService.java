package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.entity.ProjectEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing projects.
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new project based on the provided ProjectDTO.
     *
     * @param projectDTO The ProjectDTO object containing project data.
     * @return A ProjectDTO representing the created project.
     * @throws ResourceNotFoundException If the associated user is not found.
     */
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        ProjectEntity project = new ProjectEntity();
        project.setProjectName(projectDTO.getProjectName());
        project.setRpn(projectDTO.getRpn());
        project.setCompany(projectDTO.getCompany());
    
        Long userId = projectDTO.getUserId();
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        project.setUser(user);
    
        projectRepository.save(project);
    
        projectDTO.setId(project.getId());
        return projectDTO;
    }

    /**
     * Updates an existing project based on the provided ProjectDTO and project ID.
     *
     * @param projectDTO The ProjectDTO object containing updated project data.
     * @param projectId  The ID of the project to be updated.
     * @return A ProjectDTO representing the updated project.
     * @throws ResourceNotFoundException If the project with the specified ID is not found.
     */
    public ProjectDTO updateProject(ProjectDTO projectDTO, Long projectId) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            ProjectEntity project = projectOptional.get();
            // Update project fields from projectDTO
            project.setProjectName(projectDTO.getProjectName());
            project.setRpn(projectDTO.getRpn());
            project.setCompany(projectDTO.getCompany());
            // Save the updated project entity
            projectRepository.save(project);
            return projectDTO;
        } else {
            throw new ResourceNotFoundException("Project not found with id: " + projectId);
        }
    }

    private ProjectDTO converter(ProjectEntity projectEntity) {
        ProjectDTO result = new ProjectDTO();

        UserEntity user = new UserEntity();
        user.setId(projectEntity.getUser().getId());
        result.setId(projectEntity.getId());
        result.setUserId(user.getId());
        result.setProjectName(projectEntity.getProjectName());
        result.setRpn(projectEntity.getRpn());
        result.setCompany(projectEntity.getCompany());
        return result;
    }

    /**
     * Retrieves all projects and converts them to a list of ProjectDTO objects.
     *
     * @return A list of ProjectDTO objects representing the projects.
     */
    public List<ProjectDTO> getAll() {
        return projectRepository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
                
    }

    public List<ProjectDTO> getProjectsByUserId(Long id_user) {
        List<ProjectEntity> projectEntities = projectRepository.findProjectByUserId(id_user);
        return projectEntities.stream()
            .map(this::converter)
            .collect(Collectors.toList());   
        }

    /**
     * Deletes a project based on its ID.
     *
     * @param projectId The ID of the project to be deleted.
     * @return A string indicating the success of the project deletion.
     */
    public String deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
        return "DELETED";
    }
}