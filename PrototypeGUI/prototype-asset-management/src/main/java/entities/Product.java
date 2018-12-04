package entities;

import annotations.FieldName;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Product {

    @FieldName(columnName = "id")
    private int id;

    @FieldName(columnName = "date_created")
    private LocalDateTime dateCreated;

    @FieldName(columnName = "product_name")
    private String productName;

    @FieldName(columnName = "project_id")
    private int projectId;

    @FieldName(columnName = "serial_number")
    private String serialNumber;

    @FieldName(columnName = "quantity")
    private int quantity;

    @FieldName(columnName = "description")
    private String description;

    @FieldName(columnName = "status_id")
    private int statusId;

    public Product() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        if (dateCreated != null) {
            this.dateCreated = dateCreated.toLocalDateTime();
        }
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatusId() {
        return this.statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
