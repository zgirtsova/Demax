package dtos.products;

import dtos.projects.ProjectNameDTO;

import java.util.List;

public class ProductsPerPageDTO {

    private List<ProductWithDescriptionDTO> productsPerPage;

    private int filteredProductsCount;

    private List<ProjectNameDTO> projectsNames;

    public ProductsPerPageDTO() {}

    public List<ProductWithDescriptionDTO> getProductsPerPage() {
        return productsPerPage;
    }

    public void setProductsPerPage(List<ProductWithDescriptionDTO> productsPerPage) {
        this.productsPerPage = productsPerPage;
    }

    public int getFilteredProductsCount() {
        return filteredProductsCount;
    }

    public void setFilteredProductsCount(int filteredProductsCount) {
        this.filteredProductsCount = filteredProductsCount;
    }

    public List<ProjectNameDTO> getProjectsNames() {
        return projectsNames;
    }

    public void setProjectsNames(List<ProjectNameDTO> projectsNames) {
        this.projectsNames = projectsNames;
    }
}
