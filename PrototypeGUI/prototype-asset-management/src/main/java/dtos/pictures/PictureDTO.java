package dtos.pictures;

import java.io.File;

public class PictureDTO {
    private int productId;
    private File picture;
    private String fileName;

    public PictureDTO(int productId, File picture, String fileName) {
        this.productId = productId;
        this.picture = picture;
        this.fileName = fileName;
    }

    private int getProductId() {
        return productId;
    }

    private void setProductId(int productId) {
        this.productId = productId;
    }

    private File getPicture() {
        return picture;
    }

    private void setPicture(File picture) {
        this.picture = picture;
    }

    private String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
