package dev.demax.entities;

import java.sql.Timestamp;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
	@Column(columnDefinition = "VARCHAR(32) NOT NULL", name = "product_name")
	private String productName;
	
	@Column(columnDefinition = "VARCHAR(4) NOT NULL UNIQUE", name = "serial_number")
	private String serialNumber;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(columnDefinition = "TEXT", name = "description")
	private String description;
	
	@Column(name = "created_on", nullable = false)
	private Timestamp createdOn;
	
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;
	
	@OneToOne
	@JoinColumn(name = "thumbnail_id", nullable = false)
	private Picture thumbnailPic;
	
	@ManyToMany(
		cascade = CascadeType.ALL,
		targetEntity = Picture.class,
		fetch = FetchType.EAGER
	)
	@JoinTable(
		name = "products_pictures",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "picture_id")
	)
	private Set<Picture> pictures;
	
	public Product() {}

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

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public Picture getThumbnailPic() {
		return thumbnailPic;
	}

	public void setThumbnailPic(Picture thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}
}
