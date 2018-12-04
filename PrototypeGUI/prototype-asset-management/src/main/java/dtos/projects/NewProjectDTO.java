package dtos.projects;

public class NewProjectDTO {

    private String projectName;
    private String companyName;
    private String projectManager;
    private int statusId;

    public NewProjectDTO(String projectName, String companyName, String projectManager, int statusId) {
        setProjectName(projectName);
        setCompanyName(companyName);
        setProjectManager(projectManager);
        setStatusId(statusId);
    }

    public String getProjectName() {
        return projectName;
    }

    private void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyName() {
        return companyName;
    }

    private void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectManager() {
        return projectManager;
    }

    private void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public int getStatusId() {
        return statusId;
    }

    private void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}