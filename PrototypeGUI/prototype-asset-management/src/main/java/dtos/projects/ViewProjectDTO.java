package dtos.projects;

import java.util.List;

public class ViewProjectDTO {
    private int id;
    private String projectName;
    private String companyName;
    private String projectManager;
    private String status;
    private List<String> products;

    public ViewProjectDTO(int id, String projectName, String companyName, String projectManager, String status,
                          List<String> products) {
        this.id = id;
        this.projectName = projectName;
        this.companyName = companyName;
        this.projectManager = projectManager;
        this.status = status;
        this.products = products;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getProjectName() {
        return projectName;
    }

    private void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String getCompanyName() {
        return companyName;
    }

    private void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String getProjectManager() {
        return projectManager;
    }

    private void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    private String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    private List<String> getProducts() {
        return products;
    }

    private void setProducts(List<String> products) {
        this.products = products;
    }

}