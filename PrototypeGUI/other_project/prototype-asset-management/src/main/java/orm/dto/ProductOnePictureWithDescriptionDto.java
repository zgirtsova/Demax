package orm.dto;

import java.util.Date;

public class ProductOnePictureWithDescriptionDto {
	private Integer id;
	private String productName;
	private String serialNumber;
	private Integer quantity;
	private String description;
	private String projectName;
	private String picture;
	private Integer picturesCount;
	private String status;
	private Date createdOn;
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date date) {
		this.createdOn = date;
	}

	public ProductOnePictureWithDescriptionDto() {}

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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String bs) {
		this.picture = bs;
	}

	public Integer getPicturesCount() {
		return picturesCount;
	}

	public void setPicturesCount(Integer picturesCount) {
		this.picturesCount = picturesCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
