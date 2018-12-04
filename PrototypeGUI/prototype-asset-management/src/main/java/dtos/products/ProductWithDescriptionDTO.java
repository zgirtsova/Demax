package dtos.products;

import java.io.File;

public class ProductWithDescriptionDTO {
    private int id;
    private String productName;
    private String serialNumber;
    private int quantity;
    private String description;
    private String projectName;
    private File picture;
    private int picturesCount;
    private String status;

    public ProductWithDescriptionDTO(int id, String productName, String serialNumber, int quantity, String description,
                                     String projectName, File picture, int picturesCount, String status) {
        this.id = id;
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
        this.description = description;
        this.projectName = projectName;
        this.picture = picture;
        this.picturesCount = picturesCount;
        this.status = status;
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

    private String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private String getProjectName() {
        return projectName;
    }

    private void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private File getPicture() {
        return picture;
    }

    private void setPicture(File picture) {
        this.picture = picture;
    }

    private int getPicturesCount() {
        return picturesCount;
    }

    private void setPicturesCount(int picturesCount) {
        this.picturesCount = picturesCount;
    }

    private String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }
}
