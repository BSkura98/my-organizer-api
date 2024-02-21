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

    @PostMapping
    public void createProject(@RequestBody Project project) {
        projectService.createProject(project);
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @PutMapping(path="{id}")
    public void updateProject(@PathVariable("id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String description) {
        projectService.updateProject(id, name, description);
    }

    @DeleteMapping(path="{id}")
    public void deleteProject(@PathVariable("id") Long id){
        projectService.deleteProject(id);
    }
}
