package com.bskura.myorganizerapi.controller;

import com.bskura.myorganizerapi.entity.Project;
import com.bskura.myorganizerapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @PostMapping
    public void createProject(@RequestBody Project project) {
        projectService.createProject(project);
    }
}
