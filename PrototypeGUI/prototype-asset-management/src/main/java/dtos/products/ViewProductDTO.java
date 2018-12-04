package dtos.products;

import dtos.pictures.PictureDetailedDTO;
import dtos.projects.ProjectSimpleDTO;
import dtos.statuses.StatusDTO;

import java.util.List;

public class ViewProductDTO {
    private int id;
    private String productName;
    private String serialNumber;
    private int quantity;
    private String projectName;
    private List<ProjectSimpleDTO> projectNames;
    private String description;
    private String status;
    private List<StatusDTO> statuses;
    private List<PictureDetailedDTO> pictures;

    public ViewProductDTO(int id, String productName, String serialNumber, int quantity,
                          String projectName, List<ProjectSimpleDTO> projectNames, String description,
                          String status, List<StatusDTO> statuses, List<PictureDetailedDTO> pictures) {
        this.id = id;
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.projectName = projectName;
        this.projectNames = projectNames;
        this.description = description;
        this.status = status;
        this.statuses = statuses;
        this.pictures = pictures;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
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

    private String getProjectName() {
        return projectName;
    }

    private void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private List<ProjectSimpleDTO> getProjectNames() {
        return projectNames;
    }

    private void setProjectNames(List<ProjectSimpleDTO> projectNames) {
        this.projectNames = projectNames;
    }

    private String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    private List<StatusDTO> getStatuses() {
        return statuses;
    }

    private void setStatuses(List<StatusDTO> statuses) {
        this.statuses = statuses;
    }

    private List<PictureDetailedDTO> getPictures() {
        return pictures;
    }

    private void setPictures(List<PictureDetailedDTO> pictures) {
        this.pictures = pictures;
    }

}