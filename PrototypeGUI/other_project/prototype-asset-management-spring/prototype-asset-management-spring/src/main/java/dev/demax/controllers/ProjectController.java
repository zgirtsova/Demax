package dev.demax.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.demax.dtos.NewProjectDto;
import dev.demax.services.ProjectService;

@Component
@RequestMapping("/projects")
public class ProjectController {
	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping("/new")
	public String newProject() {
		projectService.test();
		return "new-project";
	}
	
	@PostMapping("/new")
	public void newProject(@ModelAttribute NewProjectDto dto) {
		projectService.createProject(dto);
	}
	
//	@GetMapping("/view/{id}")
//	public ProjectDto getDetails() {
//		return ProjectDto;
//	}
}
