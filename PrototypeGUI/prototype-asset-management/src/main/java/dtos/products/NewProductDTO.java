package dtos.products;

import dtos.pictures.PictureDTO;
import dtos.projects.ProjectSimpleDTO;
import dtos.statuses.StatusDTO;

import java.util.List;

public class NewProductDTO {

    private int projectId;
    private String projectName;
    private String productName;
    private String serialNumber;
    private int quantity;
    private String description;
    private Enum status;
    private List<PictureDTO> pictures;
    private List<ProjectSimpleDTO> projectsNames;
    private List<StatusDTO> statuses;

    public NewProductDTO(int projectId, String projectName, String productName, String serialNumber,
                         int quantity, String description, Enum status, List<PictureDTO> pictures,
                         List<ProjectSimpleDTO> projectsNames, List<StatusDTO> statuses) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
        this.pictures = pictures;
        this.projectsNames = projectsNames;
        this.statuses = statuses;
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

    private String getProductName() {
        return productName;
    }

    private void setProductName(String productName) {
        this.productName = productName;
    }

    private String getSerialNumber() {
        return serialNumber;
    }

    private void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    private int getQuantity() {
        return quantity;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private Enum getStatus() {
        return status;
    }

    private void setStatus(Enum status) {
        this.status = status;
    }

    private List<PictureDTO> getPictures() {
        return pictures;
    }

    private void setPictures(List<PictureDTO> pictures) {
        this.pictures = pictures;
    }

    private List<ProjectSimpleDTO> getProjectsNames() {
        return projectsNames;
    }

    private void setProjectsNames(List<ProjectSimpleDTO> projectsNames) {
        this.projectsNames = projectsNames;
    }

    private List<StatusDTO> getStatuses() {
        return statuses;
    }

    private void setStatuses(List<StatusDTO> statuses) {
        this.statuses = statuses;
    }
}