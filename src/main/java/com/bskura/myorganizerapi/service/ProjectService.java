package com.bskura.myorganizerapi.service;

import com.bskura.myorganizerapi.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    List<Project> projects = List.of(new Project("Project 1", "This is a project"), new Project("Project 2", "Second project"));

    public List<Project> getProjects() {
        return projects;
    }
}
