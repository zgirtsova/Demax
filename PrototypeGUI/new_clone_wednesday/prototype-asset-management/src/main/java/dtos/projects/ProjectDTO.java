package dtos.projects;

import annotations.FieldName;

import java.time.LocalDateTime;

public class ProjectDTO {

    private int id;
    private LocalDateTime dateCreated;
    private String projectName;
    private String companyName;
    private String projectManager;
    private int productsCount;
    private String status;

    public ProjectDTO() {}

    @FieldName(fieldName = "id")
    public int getId() {
        return id;
    }

    @FieldName(fieldName = "id")
    public void setId(int id) {
        this.id = id;
    }

    @FieldName(fieldName = "dateCreated")
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @FieldName(fieldName = "dateCreated")
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

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
    public void setProjectManager(String productManager) {
        this.projectManager = productManager;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}