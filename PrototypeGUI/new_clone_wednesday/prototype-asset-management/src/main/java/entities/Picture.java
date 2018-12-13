package entities;

import annotations.FieldName;
import annotations.ColumnName;

public class Picture {

    @ColumnName(columnName = "id")
    private int id;

    @ColumnName(columnName = "picture")
    private byte[] picture;

    @ColumnName(columnName = "product_id")
    private int productId;

    @ColumnName(columnName = "file_name")
    private String fileName;

    public Picture(){
    }

    @FieldName(fieldName = "id")
    public int getId() {
        return this.id;
    }

    @FieldName(fieldName = "id")
    public void setId(int id) {
        this.id = id;
    }

    @FieldName(fieldName = "picture")
    public byte[] getPicture() {
        return this.picture;
    }

    @FieldName(fieldName = "picture")
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @FieldName(fieldName = "productId")
    public int getProductId() {
        return this.productId;
    }

    @FieldName(fieldName = "productId")
    public void setProductId(int productId) {
        this.productId = productId;
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
