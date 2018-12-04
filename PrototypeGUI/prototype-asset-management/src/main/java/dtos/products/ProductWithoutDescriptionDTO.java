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

    public ProductWithoutDescriptionDTO(int id, String name, String serialNumber, int quantity, File picture,
            int picturesCount, String status) {
        this.id = id;
        this.productName = name;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
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

    private String getName() {
        return productName;
    }

    private void setName(String name) {
        this.productName = name;
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