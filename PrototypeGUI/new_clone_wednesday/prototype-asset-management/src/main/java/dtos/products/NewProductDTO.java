package dtos.products;

import annotations.FieldName;

import java.time.LocalDateTime;

public class NewProductDTO {

    private int projectId;
    private String productName;
    private String serialNumber;
    private int quantity;
    private String description;
    private int statusId;
    private LocalDateTime dateCreated;

    public NewProductDTO() {}

    @FieldName(fieldName = "projectId")
    public int getProjectId() {
        return projectId;
    }

    @FieldName(fieldName = "projectId")
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @FieldName(fieldName = "productName")
    public String getProductName() {
        return productName;
    }

    @FieldName(fieldName = "productName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @FieldName(fieldName = "serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    @FieldName(fieldName = "serialNumber")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @FieldName(fieldName = "quantity")
    public int getQuantity() {
        return quantity;
    }

    @FieldName(fieldName = "quantity")
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @FieldName(fieldName = "description")
    public String getDescription() {
        return description;
    }

    @FieldName(fieldName = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @FieldName(fieldName = "statusId")
    public int getStatusId() {
        return statusId;
    }

    @FieldName(fieldName = "statusId")
    public void setStatusId(int status) {
        this.statusId = status;
    }

    @FieldName(fieldName = "dateCreated")
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @FieldName(fieldName = "dateCreated")
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

}