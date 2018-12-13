package orm.dto;

import java.util.List;

public class ViewProductDto {
	private Integer id;
	private String productName;
	private String serialNumber;
	private Integer quantity;
	private String description;
	private String statuses;
	private List<PictureDetailedDto> pictures;
	
	public ViewProductDto() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<PictureDetailedDto> getPictures() {
		return pictures;
	}

	public void setPictures(List<PictureDetailedDto> pictures) {
		this.pictures = pictures;
	}

	public String getStatus() {
		return statuses;
	}

	public void setStatus(String string) {
		this.statuses = string;
	}
	
	
}
