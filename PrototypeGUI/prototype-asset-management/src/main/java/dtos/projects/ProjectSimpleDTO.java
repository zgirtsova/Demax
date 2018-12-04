package dtos.projects;

public class ProjectSimpleDTO {
    private int projectId;
    private String projectName;

    public ProjectSimpleDTO(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }
    
    private int getProjectId() {
        return projectId;
    }

    private void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    private String getProjectName() {
        return projectName;
    }

    private void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}