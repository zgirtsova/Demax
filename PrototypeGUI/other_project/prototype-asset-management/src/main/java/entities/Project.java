package entities;

import java.util.Date;

import util.EntityFieldData;

public class Project {
	private Integer id;
	private String name;
	private Date createdOn;
	private String companyName;
	private String projectManager;
	private Integer status;
	private Integer numOfProducts;
	
	public Project() {}

	@EntityFieldData(
		columnName = "get_id",
		type = Integer.class,
		sqlType = java.sql.Types.INTEGER
	)
	public Integer getId() {
		return id;
	}

	@EntityFieldData(
		columnName = "set_id",
		type = Integer.class,
		sqlType = java.sql.Types.INTEGER
	)
	public void setId(Integer id) {
		this.id = id;
	}

	@EntityFieldData(
		columnName = "get_project_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getName() {
		return name;
	}

	@EntityFieldData(
		columnName = "set_project_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setName(String name) {
		this.name = name;
	}

	@EntityFieldData(
		columnName = "get_date_created",
		type = Date.class,
		sqlType = java.sql.Types.DATE
	)
	public Date getCreatedOn() {
		return createdOn;
	}

	@EntityFieldData(
		columnName = "set_date_created",
		type = Date.class,
		sqlType = java.sql.Types.DATE
	)
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@EntityFieldData(
		columnName = "get_company_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getCompanyName() {
		return companyName;
	}

	@EntityFieldData(
		columnName = "set_company_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@EntityFieldData(
		columnName = "get_project_manager",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getProjectManager() {
		return projectManager;
	}

	@EntityFieldData(
		columnName = "set_project_manager",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	@EntityFieldData(
		columnName = "get_status_id",
		type = Integer.class,
		sqlType = java.sql.Types.INTEGER
	)
	public Integer getStatus() {
		return status;
	}

	@EntityFieldData(
		columnName = "set_status_id",
		type = Integer.class,
		sqlType = java.sql.Types.INTEGER
	)
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNumOfProducts() {
		return numOfProducts;
	}

	public void setNumOfProducts(Integer numOfProducts) {
		this.numOfProducts = numOfProducts;
	}	
}
