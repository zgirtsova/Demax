package services;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import entities.Picture;
import entities.Product;
import orm.dao.Dao;
import orm.dao.PictureDao;
import orm.dao.ProductDao;
import orm.dao.ProjectDao;
import orm.dto.NewProductDto;
import orm.dto.PictureDetailedDto;
import orm.dto.PictureDto;
import orm.dto.ProductOnePictureWithDescriptionDto;
import orm.dto.ProjectIdNameDto;
import orm.dto.ViewProductDto;
import util.CurrentConnection;
import util.Filter;
import util.Status;

public class ProductService {
	private static ProductService productService;
	private static ProductDao productDao;
	private static ProjectDao projectDao;
	private static PictureDao pictureDao;

	private static ActionService actionService;
	private static PictureService pictureService;
	private static ProjectService projectService;

	private ProductService() throws ClassNotFoundException, SQLException {
		productDao = ProductDao.getProductDaoInstance();
		projectDao = ProjectDao.getProjectDaoInstance();
		pictureDao = PictureDao.getPictureDaoInstance();

		actionService = ActionService.getActionServiceInstance();
		pictureService = PictureService.getPictureServiceInstance();
		projectService = ProjectService.getProjectServiceInstance();
	}

	public static final ProductService getProductServiceInstance() throws ClassNotFoundException, SQLException {
		if (productService == null) {
			productService = new ProductService();
		}

		return productService;
	}

	public List<ProductOnePictureWithDescriptionDto> filterProducts(Filter filter)
			throws SQLException, ClassNotFoundException {
		List<ProductOnePictureWithDescriptionDto> dtos;
		List<Product> products;
		Map<Integer, Picture> pictures;
		Map<Integer, Integer> picturesCount;
		Map<Integer, String> projectNames;

		CurrentConnection.beginRequest();
		try {
			dtos = new ArrayList<>();
			products = productDao.getPerPage(filter);
			pictures = pictureDao.getPicturesByProducts(products);
			picturesCount = pictureDao.getCountByProducts(products);
			projectNames = projectDao.getNamesByProducts(products);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}

		for (Product product : products) {

			ProductOnePictureWithDescriptionDto productDto = new ProductOnePictureWithDescriptionDto();
			productDto.setDescription(product.getDescription());
			productDto.setId(product.getId());

			if (pictures.get(product.getId()) != null) {
				productDto
						.setPicture(new String(Base64.getEncoder().encode(pictures.get(product.getId()).getPicture())));
			} else {
				productDto.setPicture(null);
			}

			productDto.setPicturesCount(picturesCount.get(product.getId()));
			productDto.setProductName(product.getName());
			productDto.setProjectName(projectNames.get(product.getId()));
			productDto.setSerialNumber(product.getSerialNumber());
			productDto.setStatus(Status.getStatusNameById(product.getStatus()));
			productDto.setCreatedOn(product.getCreatedOn());
			productDto.setQuantity(product.getQuantity());
			dtos.add(productDto);
		}

		return dtos;
	}

	public Integer getNumOfPages(Filter filter) throws SQLException, ClassNotFoundException {
		CurrentConnection.beginRequest();
		try {
			Integer out = productDao.getPages(filter);
			CurrentConnection.commit();
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public void createProduct(NewProductDto newProductDto, PictureDto pictureDto, String email)
			throws SQLException, IOException, ClassNotFoundException {
		Product product = new Product();
		Dao<Product> dao = new Dao<>(Product.class);
		product.setDescription(newProductDto.getDescription());
		product.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
		product.setName(newProductDto.getProductName());
		product.setQuantity(newProductDto.getQuantity());
		product.setSerialNumber(newProductDto.getSerialNumber());
		product.setStatus(Status.getStatusIdByName(newProductDto.getStatus()));

		CurrentConnection.beginRequest();
		try {
			product.setProjectId(projectDao.getByProjectName(newProductDto.getProjectName()).getId());
			dao.insert(product);
			pictureDto.setProductId(getProductIdBySerialNumber(newProductDto.getSerialNumber()));
			pictureService.createPicture(pictureDto);
			actionService.registerAction(3, email);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public Integer getProductIdBySerialNumber(String serialNumber) throws SQLException, ClassNotFoundException {
		CurrentConnection.beginRequest();
		try {
			Integer out = productDao.getProductBySerialNumber(serialNumber).getId();
			CurrentConnection.commit();
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public ViewProductDto getProductById(Integer id) throws SQLException, IOException, ClassNotFoundException {
		Product p = null;
		Dao<Product> dao = new Dao<>(Product.class);
		List<Picture> pics = null;
		List<PictureDetailedDto> outPictures = new ArrayList<>();
		CurrentConnection.beginRequest();
		try {
			p = dao.getById(id);
			pics = pictureDao.getPicturesByProductId(id);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}

		for (Picture pic : pics) {
			PictureDetailedDto pictureDto = new PictureDetailedDto();
			pictureDto.setPicture(new String(Base64.getEncoder().encode(pic.getPicture())));
			pictureDto.setId(pic.getId());
			outPictures.add(pictureDto);
		}

		ViewProductDto productDto = new ViewProductDto();
		productDto.setDescription(p.getDescription());
		productDto.setId(p.getId());
		productDto.setProductName(p.getName());
		productDto.setQuantity(p.getQuantity());
		productDto.setSerialNumber(p.getSerialNumber());
		productDto.setPictures(outPictures);
		productDto.setStatus(Status.getStatusNameById(p.getStatus()));
		return productDto;
	}

	public void changeProductStatus(Integer productId, Integer statusId) throws SQLException, ClassNotFoundException {

		Dao<Product> dao = new Dao<>(Product.class);
		CurrentConnection.beginRequest();
		try {
			Product product = dao.getById(productId);
			product.setStatus(statusId);
			dao.update(product);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public void deleteProductById(int productId) throws SQLException, IOException, ClassNotFoundException {

		Dao<Product> dao = new Dao<>(Product.class);
		CurrentConnection.beginRequest();
		try {
			List<Picture> list = pictureDao.getPicturesByProductId(productId);
			dao.delete(productId);
			for (Picture picture : list) {
				pictureDao.checkPicturesInProductsPictures(picture.getId());
			}
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}

	}

	public void updateProduct(NewProductDto newProductDto, Integer id) throws SQLException, ClassNotFoundException {

		Dao<Product> dao = new Dao<>(Product.class);
		CurrentConnection.beginRequest();
		try {
			Product p = dao.getById(id);

			if (newProductDto.getDescription() != null && newProductDto.getDescription() != "") {
				p.setDescription(newProductDto.getDescription());
			}
			if (newProductDto.getProductName() != null && newProductDto.getProductName() != "") {
				p.setName(newProductDto.getProductName());
			}
			if (newProductDto.getQuantity() != null && !(newProductDto.getQuantity() < 0)) {
				p.setQuantity(newProductDto.getQuantity());
			}
			if (newProductDto.getSerialNumber() != null && newProductDto.getSerialNumber() != "") {
				p.setSerialNumber(newProductDto.getSerialNumber());
			}

			p.setStatus(Status.getStatusIdByName(newProductDto.getStatus()));
			dao.update(p);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public Integer getNumOfProducts(Filter filter) throws SQLException, ClassNotFoundException {
		CurrentConnection.beginRequest();
		try {
			Integer out = productDao.getCount(filter);
			CurrentConnection.commit();
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public Object[] getFilteredProductsWithCountAndPagesCount(Filter filter) throws SQLException, ClassNotFoundException {
		CurrentConnection.beginRequest();
		try {
			Object[] result = new Object[4];
			Integer pagesCount = productService.getNumOfPages(filter);
			if(filter.page > pagesCount){
				filter.page = 1;
			}
			List<ProductOnePictureWithDescriptionDto> dtos = productService.filterProducts(filter);;
			List<ProjectIdNameDto> dtos2 = projectService.getProjectIdsAndNames();
			Integer productsCount = productService.getNumOfProducts(filter);
			
			result[0] = dtos;
			result[1] = dtos2;
			result[2] = productsCount;
			result[3] = pagesCount;
			
			CurrentConnection.commit();
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			CurrentConnection.rollback();
		}
		return null;
	}
}