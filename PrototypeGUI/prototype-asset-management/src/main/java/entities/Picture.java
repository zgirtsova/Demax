package entities;

import annotations.FieldName;

public class Picture {

    @FieldName(columnName = "id")
    private int id;

    @FieldName(columnName = "picture")
    private byte[] picture;

    @FieldName(columnName = "product_id")
    private int productId;

    public Picture(){
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}
