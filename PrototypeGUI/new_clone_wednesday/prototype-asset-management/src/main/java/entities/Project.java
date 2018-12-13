package entities;

import annotations.ColumnName;
import annotations.FieldName;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Project {

    @ColumnName(columnName = "id")
    private int id;

    @ColumnName(columnName = "project_name")
    private String projectName;

    @ColumnName(columnName = "date_created")
    private LocalDateTime dateCreated;

    @ColumnName(columnName = "company_name")
    private String companyName;

    @ColumnName(columnName = "project_manager")
    private String projectManager;

    @ColumnName(columnName = "status_id")
    private int statusId;

    public Project() {
    }

    @FieldName(fieldName = "id")
    public int getId() {
        return this.id;
    }

    @FieldName(fieldName = "id")
    public void setId(int id) {
        this.id = id;
    }

    @FieldName(fieldName = "projectName")
    public String getProjectName() {
        return this.projectName;
    }

    @FieldName(fieldName = "projectName")
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @FieldName(fieldName = "dateCreated")
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    @FieldName(fieldName = "dateCreated")
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @FieldName(fieldName = "companyName")
    public String getCompanyName() {
        return this.companyName;
    }

    @FieldName(fieldName = "companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @FieldName(fieldName = "projectManager")
    public String getProjectManager() {
        return this.projectManager;
    }

    @FieldName(fieldName = "projectManager")
    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    @FieldName(fieldName = "statusId")
    public int getStatusId() {
        return this.statusId;
    }

    @FieldName(fieldName = "statusId")
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

}
