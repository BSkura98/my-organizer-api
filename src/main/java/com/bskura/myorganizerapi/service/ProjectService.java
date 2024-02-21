package com.bskura.myorganizerapi.service;

import com.bskura.myorganizerapi.entity.Project;
import com.bskura.myorganizerapi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public void createProject(Project project) {
        Optional<Project> projectOptional = projectRepository.findProjectByName(project.getName());
        if(projectOptional.isPresent()) {
            throw new IllegalStateException("Project with this name already exists");
        }
        projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        boolean projectExists = projectRepository.existsById(id);
        if(!projectExists){
            throw new IllegalStateException("Project with id " + id + " does not exist");
        }
        projectRepository.deleteById(id);
    }
}
