package dev.demax.dtos;

import java.util.Set;

public class NewProductDto {
	private String projectName;
	private String productName;
	private String serialNumber;
	private Integer quantity;
	private String description;
	private String status;
	private PictureDto thumbnail;
	private Set<PictureDto> pictures;
	
	public NewProductDto() {}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PictureDto getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(PictureDto thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Set<PictureDto> getPictures() {
		return pictures;
	}

	public void setPictures(Set<PictureDto> pictures) {
		this.pictures = pictures;
	}
}
