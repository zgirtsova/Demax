package dtos.pictures;

import java.io.File;

public class PictureDetailedDTO {
    private int id;
    private File picture;
    private String fileName;
    private double fileSize;

    public PictureDetailedDTO(int id, File picture, String fileName, double fileSize) {
        this.id = id;
        this.fileName = fileName;
        this.picture = picture;
        this.fileSize = fileSize;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private File getPricture() {
        return picture;
    }

    private void setPricture(File picture) {
        this.picture = picture;
    }

    private String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private double getFileSize() {
        return fileSize;
    }

    private void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

}
