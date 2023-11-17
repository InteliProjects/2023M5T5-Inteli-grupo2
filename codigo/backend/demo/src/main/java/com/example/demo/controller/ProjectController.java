package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.model.entity.ProjectEntity;
import com.example.demo.service.ProjectService;

import java.util.List;

/**
 * A controller class that handles HTTP requests related to project operations.
 */
@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:5174/projects", allowCredentials = "true", allowedHeaders= "true")
public class ProjectController {
    /** The service responsible for project-related operations. */
    @Autowired
    private ProjectService projectService;

    /**
     * Creates a new project based on the provided project DTO.
     *
     * @param projectDTO The project DTO containing project information.
     * @return A ResponseEntity containing the created project DTO.
     */
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return ResponseEntity.ok(createdProject);
    }

    /**
     * Updates an existing project with the provided project DTO.
     *
     * @param projectId  The ID of the project to update.
     * @param projectDTO The project DTO containing updated project information.
     * @return A ResponseEntity containing the updated project DTO.
     */
    @PutMapping("/{projectId}")
    @ResponseBody
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable("projectId") Long projectId,
            @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(projectDTO, projectId);
        return ResponseEntity.ok(updatedProject);
    }

    /**
     * Retrieves a list of all projects.
     *
     * @return A list of ProjectDTO objects representing all projects.
     */
    @GetMapping
    @ResponseBody
    public List<ProjectDTO> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/user/{id_user}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByUserId(@PathVariable Long id_user) {
        List<ProjectDTO> projects = projectService.getProjectsByUserId(id_user);
        return ResponseEntity.ok(projects);
    }

    /**
     * Deletes a project with the specified project ID.
     *
     * @param projectId The ID of the project to delete.
     * @return A message indicating the result of the deletion.
     */
    @DeleteMapping("/{projectId}")
    @ResponseBody
    public String deleteProject(@PathVariable("projectId") Long projectId) {
        return projectService.deleteProject(projectId);
    }

}