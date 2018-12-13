package dtos.pictures;

import java.io.File;

public class PictureDetailedDTO {

    private int id;
    private File picture;
    private String fileName;
    private double fileSize;

    public PictureDetailedDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getPricture() {
        return picture;
    }

    public void setPricture(File picture) {
        this.picture = picture;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

}
