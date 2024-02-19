package com.bskura.myorganizerapi;

import com.bskura.myorganizerapi.entity.Project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyOrganizerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOrganizerApiApplication.class, args);
	}

	@GetMapping("/")
	public String getProject() {
		return new Project("Project", "This is simple project").toString();
	}
}
