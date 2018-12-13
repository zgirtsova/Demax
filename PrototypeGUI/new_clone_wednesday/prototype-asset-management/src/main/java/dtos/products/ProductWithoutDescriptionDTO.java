package dtos.products;

import java.io.File;

public class ProductWithoutDescriptionDTO {

    private int id;
    private String productName;
    private String serialNumber;
    private int quantity;
    private File picture;
    private int picturesCount;
    private String status;

    public ProductWithoutDescriptionDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
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

    public File getPicture() {
        return picture;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }

    public int getPicturesCount() {
        return picturesCount;
    }

    public void setPicturesCount(int picturesCount) {
        this.picturesCount = picturesCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}