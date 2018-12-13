package entities;

import java.util.Date;

import util.EntityFieldData;

public class Product {
	private Integer id;
	private String name;
	private String serialNumber;
	private Integer quantity;
	private String description;
	private Integer projectId;
	private Integer status;
	private Date createdOn;	
	
	public Product() {}
	
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
		columnName = "get_project_id",
		type = Integer.class,
		sqlType = java.sql.Types.INTEGER
	)
	public Integer getProjectId() {
		return projectId;
	}

	@EntityFieldData(
		columnName = "set_project_id",
		type = Integer.class,
		sqlType = java.sql.Types.INTEGER
	)
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@EntityFieldData(
		columnName = "get_product_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getName() {
		return name;
	}

	@EntityFieldData(
		columnName = "set_product_name",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setName(String name) {
		this.name = name;
	}

	@EntityFieldData(
		columnName = "get_serial_number",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getSerialNumber() {
		return serialNumber;
	}

	@EntityFieldData(
		columnName = "set_serial_number",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@EntityFieldData(
		columnName = "get_quantity",
		type = Integer.class,
		sqlType = java.sql.Types.INTEGER
	)
	public Integer getQuantity() {
		return quantity;
	}

	@EntityFieldData(
		columnName = "set_quantity",
		type = Date.class,
		sqlType = java.sql.Types.DATE
	)
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@EntityFieldData(
		columnName = "get_description",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public String getDescription() {
		return description;
	}

	@EntityFieldData(
		columnName = "set_description",
		type = String.class,
		sqlType = java.sql.Types.VARCHAR
	)
	public void setDescription(String description) {
		this.description = description;
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
	
	@EntityFieldData(
		columnName = "get_created_on",
		type = Date.class,
		sqlType = java.sql.Types.DATE
	)
	public Date getCreatedOn() {
		return createdOn;
	}

	@EntityFieldData(
		columnName = "set_created_on",
		type = Date.class,
		sqlType = java.sql.Types.DATE
	)
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
