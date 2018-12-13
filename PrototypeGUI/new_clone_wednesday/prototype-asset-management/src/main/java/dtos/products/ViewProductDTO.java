package dtos.products;

import dtos.pictures.PictureDetailedDTO;
import dtos.projects.ProjectNameDTO;
import dtos.statuses.StatusDTO;

import java.util.List;

public class ViewProductDTO {

    private int id;
    private String productName;
    private String serialNumber;
    private int quantity;
    private String projectName;
    private List<ProjectNameDTO> projectNames;
    private String description;
    private String status;
    private List<StatusDTO> statuses;
    private List<PictureDetailedDTO> pictures;

    public ViewProductDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ProjectNameDTO> getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(List<ProjectNameDTO> projectNames) {
        this.projectNames = projectNames;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StatusDTO> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<StatusDTO> statuses) {
        this.statuses = statuses;
    }

    public List<PictureDetailedDTO> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureDetailedDTO> pictures) {
        this.pictures = pictures;
    }

}