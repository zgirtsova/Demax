package dtos.projects;

import annotations.FieldName;

public class ProjectNameDTO {

    private int projectId;
    private String projectName;

    public ProjectNameDTO() {}

    @FieldName(fieldName = "id")
    public int getId() {
        return projectId;
    }

    @FieldName(fieldName = "id")
    public void setId(int id) {
        this.projectId = id;
    }

    @FieldName(fieldName = "projectName")
    public String getProjectName() {
        return projectName;
    }

    @FieldName(fieldName = "projectName")
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}