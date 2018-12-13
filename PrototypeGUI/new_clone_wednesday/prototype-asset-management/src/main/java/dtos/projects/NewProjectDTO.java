package dtos.projects;

import annotations.FieldName;

import java.time.LocalDateTime;

public class NewProjectDTO {

    private String projectName;
    private String companyName;
    private String projectManager;
    private int statusId;
    private LocalDateTime dateCreated;

    public NewProjectDTO() {}

    @FieldName(fieldName = "projectName")
    public String getProjectName() {
        return projectName;
    }

    @FieldName(fieldName = "projectName")
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @FieldName(fieldName = "companyName")
    public String getCompanyName() {
        return companyName;
    }

    @FieldName(fieldName = "companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @FieldName(fieldName = "projectManager")
    public String getProjectManager() {
        return projectManager;
    }

    @FieldName(fieldName = "projectManager")
    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    @FieldName(fieldName = "statusId")
    public int getStatusId() {
        return statusId;
    }

    @FieldName(fieldName = "statusId")
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @FieldName(fieldName = "dateCreated")
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @FieldName(fieldName = "dateCreated")
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}