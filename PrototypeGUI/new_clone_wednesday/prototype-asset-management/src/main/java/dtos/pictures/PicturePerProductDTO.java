package dtos.pictures;

public class PicturePerProductDTO {

    private byte[] picture;

    private int picturesCount;

    public PicturePerProductDTO() {}

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getPicturesCount() {
        return picturesCount;
    }

    public void setPicturesCount(int picturesCount) {
        this.picturesCount = picturesCount;
    }
}
