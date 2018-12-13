package dev.demax.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.demax.dtos.NewProjectDto;
import dev.demax.entities.Project;
import dev.demax.entities.Status;
import dev.demax.finders.ProjectFinder;
import dev.demax.finders.StatusFinder;
import util.Filter;

@Component
@Transactional
public class ProjectService {
	private final ProjectFinder projectFinder;
	private final StatusFinder statusFinder;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ProjectService(ProjectFinder projectFinder, StatusFinder statusFinder, ModelMapper modelMapper) {
		this.projectFinder = projectFinder;
		this.statusFinder = statusFinder;
		this.modelMapper = modelMapper;
	}
	
	public Project findProjectById(UUID id) {
		return this.projectFinder.getById(id);
	}

	public void createProject(NewProjectDto dto) {
		Project project = modelMapper.map(dto, Project.class);
		Status status = statusFinder.getStatusById(util.Status.getStatusIdByName("New"));
		project.setStatus(status);
		project.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
		
		projectFinder.insert(project);
	}
	
	public void test() {
		Filter filter = new Filter(null, Timestamp.valueOf("1111-11-11 11:11:11"), null, null, null, null, null, null, null);
		System.out.println(projectFinder.getCount(filter));
	}
}
