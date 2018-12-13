package entities;

import annotations.ColumnName;
import annotations.FieldName;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Product {

    @ColumnName(columnName = "id")
    private int id;

    @ColumnName(columnName = "date_created")
    private LocalDateTime dateCreated;

    @ColumnName(columnName = "product_name")
    private String productName;

    @ColumnName(columnName = "project_id")
    private int projectId;

    @ColumnName(columnName = "serial_number")
    private String serialNumber;

    @ColumnName(columnName = "quantity")
    private int quantity;

    @ColumnName(columnName = "description")
    private String description;

    @ColumnName(columnName = "status_id")
    private int statusId;

    public Product() {}

    @FieldName(fieldName = "id")
    public int getId() {
        return this.id;
    }

    @FieldName(fieldName = "id")
    public void setId(int id) {
        this.id = id;
    }

    @FieldName(fieldName = "dateCreated")
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    @FieldName(fieldName = "dateCreated")
    public void setDateCreated(LocalDateTime dateCreated) {
            this.dateCreated = dateCreated;
    }

    @FieldName(fieldName = "productName")
    public String getProductName() {
        return this.productName;
    }

    @FieldName(fieldName = "productName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @FieldName(fieldName = "projectId")
    public int getProjectId() {
        return this.projectId;
    }

    @FieldName(fieldName = "projectId")
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @FieldName(fieldName = "serialNumber")
    public String getSerialNumber() {
        return this.serialNumber;
    }

    @FieldName(fieldName = "serialNumber")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @FieldName(fieldName = "quantity")
    public int getQuantity() {
        return this.quantity;
    }

    @FieldName(fieldName = "quantity")
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @FieldName(fieldName = "description")
    public String getDescription() {
        return this.description;
    }

    @FieldName(fieldName = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @FieldName(fieldName = "statusId")
    public int getStatusId() {
        return this.statusId;
    }

    @FieldName(fieldName = "statusId")
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
