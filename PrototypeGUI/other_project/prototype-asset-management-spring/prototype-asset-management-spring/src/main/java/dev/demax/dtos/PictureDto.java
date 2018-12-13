package dev.demax.dtos;

public class PictureDto {
	private byte[] picture;
	private String pictureName;
	
	public PictureDto() {}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
}
