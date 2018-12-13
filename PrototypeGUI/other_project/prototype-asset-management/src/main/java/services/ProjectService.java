package services;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Picture;
import entities.Product;
import entities.Project;
import orm.dao.Dao;
import orm.dao.PictureDao;
import orm.dao.ProjectDao;
import orm.dto.NewProjectDto;
import orm.dto.ProductOnePictureWithoutDescriptionDto;
import orm.dto.ProjectDto;
import orm.dto.ProjectIdNameDto;
import orm.dto.ViewProjectDto;
import util.CurrentConnection;
import util.Filter;
import util.Status;

public class ProjectService {
	private static ProjectService projectService;
	private static ProjectDao projectDao;
	private static PictureDao pictureDao;
	private static Dao<Project> dao;
	private static ActionService actionService;

	private ProjectService() throws ClassNotFoundException, SQLException {
		projectDao = ProjectDao.getProjectDaoInstance();
		pictureDao = PictureDao.getPictureDaoInstance();
		dao = new Dao<>(Project.class);
		actionService = ActionService.getActionServiceInstance();
	}

	public static final ProjectService getProjectServiceInstance() throws ClassNotFoundException, SQLException {
		if (projectService == null) {
			projectService = new ProjectService();
		}

		return projectService;
	}

	public void createProject(NewProjectDto newProjectDto, String email) throws SQLException, ClassNotFoundException {
		Project project = new Project();
		project.setCompanyName(newProjectDto.getCompanyName());
		project.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
		project.setName(newProjectDto.getProjectName());
		project.setProjectManager(newProjectDto.getProjectManager());
		project.setStatus(Status.getStatusIdByName(newProjectDto.getStatus()));

		CurrentConnection.beginRequest();
		try {
			dao.insert(project);
			actionService.registerAction(4, email);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public List<String> getProjectNames() throws SQLException, ClassNotFoundException {
		List<Project> projects;
		List<String> names = new ArrayList<>();
		CurrentConnection.beginRequest();
		try {
			projects = projectDao.getAll();
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}

		for (Project project : projects) {
			names.add(project.getName());
		}

		return names;
	}

	public List<ProjectIdNameDto> getProjectIdsAndNames() throws SQLException, ClassNotFoundException {
		List<Project> projects;
		List<ProjectIdNameDto> idsAndNames = new ArrayList<>();
		CurrentConnection.beginRequest();
		try {
			projects = projectDao.getAll();
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}

		for (Project project : projects) {
			ProjectIdNameDto dto = new ProjectIdNameDto();
			dto.setId(project.getId());
			dto.setName(project.getName());
			idsAndNames.add(dto);
		}

		return idsAndNames;
	}

	public void changeProjectStatus(Integer projectId, Integer statusId) throws SQLException, ClassNotFoundException {
		CurrentConnection.beginRequest();
		try {
			Project project = dao.getById(projectId);
			project.setStatus(statusId);
			dao.update(project);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public List<ProjectDto> filterProjects(Filter filter) throws SQLException, ParseException, ClassNotFoundException {
		List<Project> projects;
		CurrentConnection.beginRequest();
		try {
			projects = projectDao.getPerPage(filter);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
		return convertToDto(projects);
	}

	public void deleteProjectById(int projectId) throws SQLException, IOException, ClassNotFoundException {
		Set<Picture> pictures = new HashSet<>();
		CurrentConnection.beginRequest();
		try {
			List<Product> products = projectDao.getProductsByProjectId(projectId);

			for (Product product : products) {
				pictures.addAll(pictureDao.getPicturesByProductId(product.getId()));
			}

			dao.delete(projectId);
			for (Picture picture : pictures) {
				pictureDao.checkPicturesInProductsPictures(picture.getId());
			}
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public ViewProjectDto getProjectById(Integer projectId) throws SQLException, IOException, ClassNotFoundException {
		ViewProjectDto projectDto = new ViewProjectDto();
		Project project;
		List<ProductOnePictureWithoutDescriptionDto> dtos = new ArrayList<>();
		CurrentConnection.beginRequest();
		try {
			project = dao.getById(projectId);
			List<Product> products = projectDao.getProductsByProjectId(projectId);

			for (int i = 0; i < products.size(); i++) {
				ProductOnePictureWithoutDescriptionDto dto = new ProductOnePictureWithoutDescriptionDto();
				Integer productId = products.get(i).getId();
				dto.setId(productId);
				dto.setName(products.get(i).getName());
				List<Picture> pictures = pictureDao.getPicturesByProductId(productId);
				dto.setPicture(new String(Base64.getEncoder().encode(pictures.get(0).getPicture())));
				dto.setQuantity(products.get(i).getQuantity());
				dto.setSerialNumber(products.get(i).getSerialNumber());
				dto.setStatus(Status.getStatusNameById(products.get(i).getStatus()));
				dtos.add(dto);
			}
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}

		projectDto.setCompanyName(project.getCompanyName());
		projectDto.setId(project.getId());
		projectDto.setProducts(dtos);
		projectDto.setProjectName(project.getName());
		projectDto.setProjectManager(project.getProjectManager());
		projectDto.setStatus(Status.getStatusNameById(project.getStatus()));

		return projectDto;
	}

	public void updateProject(NewProjectDto dto, Integer projectId) throws SQLException, ClassNotFoundException {
		CurrentConnection.beginRequest();
		try {
			Project project = dao.getById(projectId);
			project.setCompanyName(dto.getCompanyName());
			project.setName(dto.getProjectName());
			project.setProjectManager(dto.getProjectManager());
			project.setStatus(Status.getStatusIdByName(dto.getStatus()));
			dao.update(project);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public Integer getNumOfPages(Filter filter) throws SQLException {
		Integer numOfPages;
		CurrentConnection.beginRequest();
		try {
			numOfPages = projectDao.getPages(filter);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
		return numOfPages;
	}

	public Integer getProjectCount(Filter filter) throws SQLException, ClassNotFoundException {
		Integer projectsCount;
		CurrentConnection.beginRequest();
		try {
			projectsCount = projectDao.getCount(filter);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
		return projectsCount;
	}

	public Object[] getFilteredProjectsWithCountAndPagesCount(Filter filter)
			throws SQLException, ParseException, ClassNotFoundException {
		CurrentConnection.beginRequest();
		try {
			Object[] result = new Object[3];
			Integer pagesCount = projectService.getNumOfPages(filter);
			if(filter.page > pagesCount){
				filter.page = 1;
			}
			List<ProjectDto> dtos = projectService.filterProjects(filter);
			Integer projectsCount = projectService.getProjectCount(filter);
			result[0] = dtos;
			result[1] = projectsCount;
			result[2] = pagesCount;
			CurrentConnection.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	private List<ProjectDto> convertToDto(List<Project> projects) throws SQLException, ClassNotFoundException {
		List<ProjectDto> list = new ArrayList<>();
		CurrentConnection.beginRequest();
		try {
			for (Project project : projects) {
				ProjectDto projectDto = new ProjectDto();
				projectDto.setCompanyName(project.getCompanyName());
				projectDto.setCreatedOn(project.getCreatedOn());
				projectDto.setId(project.getId());
				projectDto.setProductsCount(projectDao.getProductsCountByProjectId(project.getId()));
				projectDto.setProjectManager(project.getProjectManager());
				projectDto.setProjectName(project.getName());
				projectDto.setStatus(Status.getStatusNameById(project.getStatus()));

				list.add(projectDto);
			}
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}

		return list;
	}
}
