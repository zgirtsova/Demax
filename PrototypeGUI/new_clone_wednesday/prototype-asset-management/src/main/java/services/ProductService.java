package services;

import daos.ProductDao;
import daos.ProjectDao;
import dtos.pictures.PictureDTO;
import dtos.products.NewProductDTO;
import dtos.products.ProductWithDescriptionDTO;
import dtos.products.ProductsPerPageDTO;
import entities.Product;
import entities.Status;
import helpers.Filter;
import helpers.ModelMapper;
import singletons.ConnectionPoolSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService {

    private ProductDao productDao;
    private ProjectDao projectDao;
    private PictureService pictureService;
    private ProjectService projectService;
    private ModelMapper modelMapper;

    public ProductService() {
        productDao = new ProductDao();
        projectDao = new ProjectDao();
        pictureService = new PictureService();
        projectService = new ProjectService();
        modelMapper = new ModelMapper();
    }

    public ProductsPerPageDTO getProductsPerPage(int resultsPerPage, int page, List<Filter> filters) {

        ProductsPerPageDTO productsPerPageDTO = new ProductsPerPageDTO();

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            Map.Entry<List<Product>, Integer> productsEntry;

            productsEntry = productDao.getPerPage(resultsPerPage, page, filters);

            List<Product> products = productsEntry.getKey();
            int filteredProductsCount = productsEntry.getValue();

            List<ProductWithDescriptionDTO> productsPerPage = new ArrayList<>();

            if (products == null) {
                productsPerPageDTO.setProductsPerPage(null);
            } else {
                for (Product product : products) {
                    ProductWithDescriptionDTO productWithDescriptionDTO = (ProductWithDescriptionDTO) modelMapper.map(product, ProductWithDescriptionDTO.class);
                    productWithDescriptionDTO.setPictureDTO(pictureService.getPicturePerProduct(product.getId()));
                    productWithDescriptionDTO.setStatus(Status.statuses.get(product.getStatusId()));
                    productWithDescriptionDTO.setProjectName(projectDao.getProjectName(product.getProjectId()));

                    productsPerPage.add(productWithDescriptionDTO);
                }

                productsPerPageDTO.setProductsPerPage(productsPerPage);
            }

            productsPerPageDTO.setFilteredProductsCount(filteredProductsCount);
            productsPerPageDTO.setProjectsNames(projectService.getProjectsNames());

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

        return productsPerPageDTO;
    }

    public void updateProductsStatusesByIds(int[] productsIds, int actionId) {

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            productDao.updateStatusByIds(productsIds, actionId);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }
    }

    public void deleteProductsByIds(int[] productsIds) {

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            productDao.deleteByIds(productsIds);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }
    }

    public void addNewProduct(NewProductDTO productDTO, PictureDTO pictureDTO) {

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            Product product = (Product) modelMapper.map(productDTO, Product.class);

            productDao.insert(product);

            int productId = productDao.getProductId(product.getSerialNumber());

            PictureService pictureService = new PictureService();
            pictureDTO.setProductId(productId);
            pictureService.addPicture(pictureDTO);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

    }
}
