package dtos.pictures;

import annotations.FieldName;

public class PictureDTO {

    private byte[] picture;
    private int productId;
    private String fileName;

    public PictureDTO() {}

    @FieldName(fieldName = "productId")
    public int getProductId() {
        return productId;
    }

    @FieldName(fieldName = "productId")
    public void setProductId(int productId) {
        this.productId = productId;
    }

    @FieldName(fieldName = "picture")
    public byte[] getPicture() {
        return picture;
    }

    @FieldName(fieldName = "picture")
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @FieldName(fieldName = "fileName")
    public String getFileName() {
        return fileName;
    }

    @FieldName(fieldName = "fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
