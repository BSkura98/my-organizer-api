package com.bskura.myorganizerapi.config;

import com.bskura.myorganizerapi.entity.Project;
import com.bskura.myorganizerapi.repository.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProjectConfig {
    @Bean
    CommandLineRunner commandLineRunner (ProjectRepository repository) {
        return args -> {Project project1 = new Project("Project 1", "This is a project");
            Project project2 = new Project("Project 2", "Second project");

            repository.saveAll(List.of(project1, project2));
        };
    }
}
