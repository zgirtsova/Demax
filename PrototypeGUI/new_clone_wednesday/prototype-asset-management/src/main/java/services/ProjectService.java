package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import daos.ProductDao;
import daos.ProjectDao;
import dtos.projects.NewProjectDTO;
import dtos.projects.ProjectDTO;
import dtos.projects.ProjectNameDTO;
import dtos.projects.ProjectsPerPageDTO;
import entities.Project;
import entities.Status;
import helpers.Filter;
import helpers.ModelMapper;
import singletons.ConnectionPoolSingleton;

public class ProjectService {

    private ProjectDao projectDao;
    private ProductDao productDao;
    private ModelMapper modelMapper;

    public ProjectService() {
        this.projectDao = new ProjectDao();
        this.productDao = new ProductDao();
        this.modelMapper = new ModelMapper();
    }

    public ProjectsPerPageDTO getProjectsPerPage(int resultsPerPage, int page, List<Filter> filters) {

        ProjectsPerPageDTO projectsPerPageDTO = new ProjectsPerPageDTO();

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            Map.Entry<List<Project>, Integer> projectsEntry;

            projectsEntry = projectDao.getPerPage(resultsPerPage, page, filters);

            List<Project> projects = projectsEntry.getKey();
            int filteredProjectsCount = projectsEntry.getValue();

            List<ProjectDTO> projectsPerPage = new ArrayList<>();

            if (projects == null) {
                projectsPerPageDTO.setProjectsPerPage(null);
            } else {
                for (Project project : projects) {

                    ProjectDTO projectDTO = (ProjectDTO) modelMapper.map(project, ProjectDTO.class);
                    projectDTO.setProductsCount(productDao.getProductsByProjectIdCount(project.getId()));
                    projectDTO.setStatus(Status.statuses.get(project.getStatusId()));

                    projectsPerPage.add(projectDTO);
                }

                projectsPerPageDTO.setProjectsPerPage(projectsPerPage);
            }

            projectsPerPageDTO.setFilteredProjectsCount(filteredProjectsCount);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

        return projectsPerPageDTO;
    }

    public void updateProjectsStatusesByIds(int[] projectsIds, int actionId) {

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            projectDao.updateStatusByIds(projectsIds, actionId);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }
    }

    public void deleteProjectsByIds(int[] projectsIds) {

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            projectDao.deleteByIds(projectsIds);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }
    }

    public void addNewProject(NewProjectDTO projectDTO) {

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            Project project = (Project) modelMapper.map(projectDTO, Project.class);

            projectDao.insert(project);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }
    }

    public List<ProjectNameDTO> getProjectsNames() {

        List<ProjectNameDTO> projectNameDTOs = new ArrayList<>();

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            List<Project> projects = projectDao.getAll();

            for (Project project : projects) {
                ProjectNameDTO projectNameDTO = (ProjectNameDTO) modelMapper.map(project, ProjectNameDTO.class);
                projectNameDTOs.add(projectNameDTO);
            }

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

        return projectNameDTOs;
    }
}