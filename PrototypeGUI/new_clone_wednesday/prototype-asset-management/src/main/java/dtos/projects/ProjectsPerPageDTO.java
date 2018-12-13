package dtos.projects;

import java.util.List;

public class ProjectsPerPageDTO {

    private List<ProjectDTO> projectsPerPage;

    private int filteredProjectsCount;

    public ProjectsPerPageDTO() {}

    public List<ProjectDTO> getProjectsPerPage() {
        return projectsPerPage;
    }

    public void setProjectsPerPage(List<ProjectDTO> projectsPerPage) {
        this.projectsPerPage = projectsPerPage;
    }

    public int getFilteredProjectsCount() {
        return filteredProjectsCount;
    }

    public void setFilteredProjectsCount(int filteredProjectsCount) {
        this.filteredProjectsCount = filteredProjectsCount;
    }
}
