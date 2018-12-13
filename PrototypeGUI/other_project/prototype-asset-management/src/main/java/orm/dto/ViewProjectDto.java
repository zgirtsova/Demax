package orm.dto;

import java.util.List;

public class ViewProjectDto {
	private Integer id;
	private String projectName;
	private String companyName;
	private String projectManager;
	private String status;
	private List<ProductOnePictureWithoutDescriptionDto> products;
	
	public ViewProjectDto() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ProductOnePictureWithoutDescriptionDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductOnePictureWithoutDescriptionDto> products) {
		this.products = products;
	}
	
	
}
