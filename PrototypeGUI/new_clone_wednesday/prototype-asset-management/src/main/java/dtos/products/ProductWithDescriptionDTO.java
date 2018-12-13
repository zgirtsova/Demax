package dtos.products;

import annotations.FieldName;
import dtos.pictures.PicturePerProductDTO;

import java.time.LocalDateTime;

public class ProductWithDescriptionDTO {

    private int id;
    private LocalDateTime dateCreated;
    private String productName;
    private String serialNumber;
    private int quantity;
    private String description;
    private String projectName;
    private String status;
    private PicturePerProductDTO pictureDTO;

    public ProductWithDescriptionDTO() {}

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PicturePerProductDTO getPictureDTO() {
        return pictureDTO;
    }

    public void setPictureDTO(PicturePerProductDTO pictureDTO) {
        this.pictureDTO = pictureDTO;
    }
}
