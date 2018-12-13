package dev.demax.entities;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {	
	@Column(name = "created_on", nullable = false)
	private Timestamp createdOn;
	
	@Column(columnDefinition = "VARCHAR(32) UNIQUE NOT NULL", name = "project_name")
	private String projectName;
	
	@Column(columnDefinition = "VARCHAR(32) NOT NULL", name = "company_name")
	private String companyName;
	
	@Column(columnDefinition = "VARCHAR(32) NOT NULL", name = "project_manager")
	private String projectManager;
	
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;
	
	@OneToMany(targetEntity = Product.class, mappedBy = "project", fetch = FetchType.EAGER)
	private Set<Product> products;
	
	public Project() {}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
