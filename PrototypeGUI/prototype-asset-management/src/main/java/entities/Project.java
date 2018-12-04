package entities;

import annotations.FieldName;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Project {

    @FieldName(columnName = "id")
    private int id;

    @FieldName(columnName = "project_name")
    private String projectName;

    @FieldName(columnName = "date_created")
    private LocalDateTime dateCreated;

    @FieldName(columnName = "company_name")
    private String companyName;

    @FieldName(columnName = "project_manager")
    private String projectManager;

    @FieldName(columnName = "status_id")
    private int statusId;

    public Project() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        if (dateCreated != null) {
            this.dateCreated = dateCreated.toLocalDateTime();
        }
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectManager() {
        return this.projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public int getStatusId() {
        return this.statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

}
