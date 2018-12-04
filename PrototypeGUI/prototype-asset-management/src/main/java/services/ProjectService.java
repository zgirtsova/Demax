package services;

import java.sql.Timestamp;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import daos.ProductDao;
import daos.ProjectDao;
import dtos.projects.NewProjectDTO;
import dtos.projects.ProjectDTO;
import entities.Project;
import entities.Status;
import helpers.Filter;
import singletons.ConnectionPoolSingleton;

public class ProjectService {

    private ProjectDao projectDao;
    private ProductDao productDao;

    public ProjectService() {
        this.projectDao = new ProjectDao();
        this.productDao = new ProductDao();
    }

    public List<ProjectDTO> getProjectsPerPage(int resultsPerPage, int page, List<Filter> filters) throws InterruptedException {
        Connection connection = ConnectionPoolSingleton.getConnection();

        Map.Entry<List<Project>, Integer> projectsEntry;

        if (filters == null) {
            projectsEntry = projectDao.getPerPage(resultsPerPage, page);
        } else {
            projectsEntry = projectDao.getPerPage(resultsPerPage, page, filters);
        }

        ConnectionPoolSingleton.retrieveConnection();

        List<Project> projects = projectsEntry.getKey();

        // TODO: pagination with filteredCount
        //int filteredCount = projectsEntry.getValue();

        if (projects == null) {
            return null;
        }

        return projects.stream()
                .map(p -> new ProjectDTO(p.getId(), p.getDateCreated(), p.getProjectName(), p.getCompanyName(),
                        p.getProjectManager(), this.productDao.getProductsByProjectIdCount(p.getId()),
                        Status.statuses.get(p.getStatusId())))
                .collect(Collectors.toList());
    }

    public void updateProjectsStatusesByIds(int[] projectsIds, int actionId) {
        projectDao.updateStatusByIds(projectsIds, actionId);
    }

    public void deleteProjectsByIds(int[] projectsIds) {
        projectDao.deleteByIds(projectsIds);
    }

    public void addNewProject(NewProjectDTO projectDTO) {

        Project project = new Project();

        project.setProjectName(projectDTO.getProjectName());
        project.setDateCreated(new Timestamp(System.currentTimeMillis()));
        project.setCompanyName(projectDTO.getCompanyName());
        project.setProjectManager(projectDTO.getProjectManager());
        project.setStatusId(projectDTO.getStatusId());

        projectDao.insert(project);
    }
}