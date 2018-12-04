package dtos.projects;

import java.time.LocalDateTime;

public class ProjectDTO {
    private int id;
    private LocalDateTime createdOn;
    private String projectName;
    private String companyName;
    private String projectManager;
    private int productsCount;
    private String status;

    public ProjectDTO(int id, LocalDateTime createdOn, String projectName, String companyName, String projectManager,
            int productsCount, String status) {
        this.id = id;
        this.createdOn = createdOn;
        this.projectName = projectName;
        this.companyName = companyName;
        this.projectManager = projectManager;
        this.productsCount = productsCount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return this.createdOn;
    }

    private void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
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

    private void setProjectManager(String productManager) {
        this.projectManager = productManager;
    }

    public int getProductsCount() {
        return productsCount;
    }

    private void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

}