package com.bskura.myorganizerapi.service;

import com.bskura.myorganizerapi.entity.Project;
import com.bskura.myorganizerapi.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping
    public void createProject(Project project) {
        Optional<Project> projectOptional = projectRepository.findProjectByName(project.getName());
        if(projectOptional.isPresent()) {
            throw new IllegalStateException("Project with this name already exists");
        }
        project.setCreationDate(LocalDate.now());
        projectRepository.save(project);
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Transactional
    public void updateProject(Long id, String name, String description) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new IllegalStateException("Project with id " + id + " does not exist"));

        if(name != null && !name.isEmpty() && !Objects.equals(project.getName(), name)){
            Optional<Project> projectOptional = projectRepository.findProjectByName(name);
            if(projectOptional.isPresent()){
                throw new IllegalStateException("Project with this name already exists");
            }
            project.setName(name);
        }

        if (description != null && !description.isEmpty() && !Objects.equals(project.getDescription(), description)){
            project.setDescription(description);
        }
    }

    public Project updateProject(Long id, Map<String, Object> fields) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new IllegalStateException("Project with id " + id + " does not exist"));

        fields.forEach((key, value) -> {
            if(!(key.equals("id") || key.equals("creationDate"))){
                if(Objects.equals(key, "name")){
                    if(value != null && !((String)value).isEmpty() && !Objects.equals(project.getName(), value)){
                        Optional<Project> projectOptional = projectRepository.findProjectByName((String)value);
                        if(projectOptional.isPresent()){
                            throw new IllegalStateException("Project with this name already exists");
                        }
                    }
                }

                Field field = ReflectionUtils.findField(Project.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, project, value);
            }
        });
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        boolean projectExists = projectRepository.existsById(id);
        if(!projectExists){
            throw new IllegalStateException("Project with id " + id + " does not exist");
        }
        projectRepository.deleteById(id);
    }
}
