package orm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Product;
import entities.Project;
import util.CurrentConnection;
import util.Filter;

public class ProjectDao extends Dao<Project> {
	private static ProjectDao projectDao;
	protected String getCountStatement;
	protected String getFinishedCountStatement;
	protected String getNamesById;
	protected String getFilteredCountStatement;
	protected HashMap<Byte, String> getAllStatements;

	private ProjectDao() throws SQLException, ClassNotFoundException {
		super();
		getCountStatement = "SELECT COUNT(*) FROM projects";
		getFinishedCountStatement = "SELECT COUNT(*) FROM projects WHERE status_id = ?";
		getNamesById = "SELECT project_name FROM projects INNER JOIN products "
				+ "ON projects.id = products.project_id WHERE products.id = ? GROUP BY project_name";
		getFilteredCountStatement = "WITH variables(date_from, date_to, statusId) "
				+ "as (VALUES(CAST(? as Timestamp), CAST(? as Timestamp), "
				+ "CAST(? as Integer))) SELECT COUNT(*) FROM projects, variables "
				+ "WHERE (date_created >= variables.date_from OR variables.date_from IS NULL) "
				+ "AND (date_created <= variables.date_to OR variables.date_to IS NULL) "
				+ "AND (status_id = variables.statusId OR variables.statusId IS NULL)";
		getAllStatements = new HashMap<>();
	}

	public static final ProjectDao getProjectDaoInstance() 
			throws ClassNotFoundException, SQLException {
		if (projectDao == null) {
			projectDao = new ProjectDao();
		}

		return projectDao;
	}

	public List<Project> getPerPage(Filter filter) 
			throws ClassNotFoundException {
		String keyI = "";
		keyI += (filter.from == null)      ? 1 : 0;
		keyI += (filter.to == null)        ? 1 : 0;
		keyI += (filter.statusId == null)  ? 1 : 0;
		keyI += (filter.projectId == null) ? 1 : 0;
		keyI += Integer.toBinaryString(filter.sortRule.intValue());
		Byte key = Byte.parseByte(keyI, 2);
		
		if (!getAllStatements.containsKey(key)) {
			getAllStatements.put(key, buildAllQuery(filter));
		}

		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement(getAllStatements.get(key));
			ps.clearParameters();
			int counter = 1;
			
			if (filter.from != null) {
				ps.setTimestamp(counter, filter.from);
				counter++;
			}
			if (filter.to != null) {
				ps.setTimestamp(counter, filter.to);
				counter++;
			}
			if (filter.statusId != null) {
				ps.setInt(counter, filter.statusId);
				counter++;
			}
			
			ps.setInt(counter++, filter.perPage);
			ps.setInt(counter++, (filter.page - 1) * filter.perPage);
			List<Project> projects = new ArrayList<Project>();
			ResultSet res = ps.executeQuery();
			Project project = null;
			
			while (res.next()) {
				project = new Project();
				project.setId(res.getInt(1));
				project.setCreatedOn(res.getDate(2));
				project.setName(res.getString(3));
				project.setCompanyName(res.getString(4));
				project.setProjectManager(res.getString(5));
				project.setStatus(res.getInt(6));
				project.setNumOfProducts(res.getInt(7));
				projects.add(project);
			}
			
			ps.close();
			return projects;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected String buildAllQuery(Filter filter) {
		StringBuilder query = new StringBuilder();
		query.append(
				"SELECT projects.*,COALESCE(cnt,0 ) as prodCnt "
				+ "FROM projects LEFT JOIN (SELECT project_id, "
				+ "COUNT(project_id)as cnt FROM products GROUP BY project_id) "
				+ "as t ON projects.id = t.project_id WHERE 1 = 1 ");
		
		if (filter.from != null) {
			query.append(" AND date_created >= ? ");
		}
		if (filter.to != null) {
			query.append(" AND date_created <= ? ");
		}
		if (filter.statusId != null) {
			query.append(" AND status_id = ? ");
		}
		
		switch (filter.sortRule) {
			case 1:
				query.append("ORDER BY date_created ASC, prodCnt ASC");
				break;
			case 2:
				query.append("ORDER BY date_created ASC, prodCnt DESC");
				break;
			case 3:
				query.append("ORDER BY date_created DESC, prodCnt ASC");
				break;
			case 4:
				query.append("ORDER BY date_created DESC, prodCnt DESC");
				break;
			case 5:
				query.append("ORDER BY prodCnt ASC, date_created ASC");
				break;
			case 6:
				query.append("ORDER BY prodCnt ASC, date_created DESC");
				break;
			case 7:
				query.append("ORDER BY prodCnt DESC, date_created ASC");
				break;
			case 8:
				query.append("ORDER BY prodCnt DESC, date_created DESC");
				break;
		}
		
		query.append(" LIMIT ? OFFSET ?");
		return query.toString();
	}

	public Integer getCount(Filter filter) {
		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement(getFilteredCountStatement);
			
			if (filter.from != null) {
				ps.setTimestamp(1, filter.from);
			} else {
				ps.setNull(1, java.sql.Types.TIMESTAMP);
			}
			if (filter.to != null) {
				ps.setTimestamp(2, filter.to);
			} else {
				ps.setNull(2, java.sql.Types.TIMESTAMP);
			}
			if (filter.statusId != null) {
				ps.setInt(3, filter.statusId);
			} else {
				ps.setNull(3, java.sql.Types.INTEGER);
			}
			
			ResultSet res = ps.executeQuery();
			
			while (res.next()) {
				return res.getInt(1);
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Integer getFinishedCount() 
			throws ClassNotFoundException {
		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement("SELECT COUNT(*) "
							+ "FROM projects WHERE status_id = 3");
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				Integer count = resultSet.getInt(1);
				ps.close();
				return count;
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer getNewCount() throws ClassNotFoundException {
		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement("SELECT COUNT(*) "
							+ "FROM projects WHERE status_id = 1");
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				Integer count = resultSet.getInt(1);
				ps.close();
				return count;
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Project> getAll() 
			throws SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement("SELECT * FROM projects");
		ResultSet resultSet = ps.executeQuery();
		List<Project> projectList = new ArrayList<>();

		while (resultSet.next()) {
			Project project = new Project();
			project.setCompanyName(resultSet.getString("company_name"));
			project.setCreatedOn(resultSet.getDate("date_created"));
			project.setId(resultSet.getInt("id"));
			project.setName(resultSet.getString("project_name"));
			project.setProjectManager(resultSet.getString("project_manager"));
			project.setStatus(resultSet.getInt("status_id"));
			projectList.add(project);
		}

		ps.close();
		return projectList;
	}

	public Project getByProjectName(String projectName) 
			throws ClassNotFoundException {
		if (projectName == null) {
			return null;
		}
		
		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement("SELECT * FROM projects WHERE project_name = ?");
			ps.setString(1, projectName);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				Project project = new Project();
				project.setCompanyName(resultSet.getString("company_name"));
				project.setCreatedOn(resultSet.getDate("date_created"));
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("project_name"));
				project.setProjectManager(resultSet.getString("project_manager"));
				project.setStatus(resultSet.getInt("status_id"));

				return project;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Integer getProductsCountByProjectId(Integer projectId) 
			throws SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent().prepareStatement(
				"SELECT COUNT(*) FROM projects INNER JOIN products "
				+ "ON projects.id = products.project_id WHERE project_id = ?");
		ps.setInt(1, projectId);
		ResultSet resultSet = ps.executeQuery();

		if (resultSet.next()) {
			Integer count = resultSet.getInt(1);
			ps.close();
			return count;
		}
		
		ps.close();
		return 0;
	}

	public List<Product> getProductsByProjectId(Integer projectId) 
			throws SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent().prepareStatement(
				"SELECT * FROM products INNER JOIN projects "
				+ "ON projects.id = products.project_id WHERE project_id = ?");
		ps.setInt(1, projectId);
		ResultSet resultSet = ps.executeQuery();
		List<Product> productList = new ArrayList<>();
		
		while (resultSet.next()) {
			Product product = new Product();
			product.setCreatedOn(resultSet.getDate("created_on"));
			product.setDescription(resultSet.getString("description"));
			product.setId(resultSet.getInt("id"));
			product.setName(resultSet.getString("product_name"));
			product.setProjectId(resultSet.getInt("project_id"));
			product.setQuantity(resultSet.getInt("quantity"));
			product.setSerialNumber(resultSet.getString("serial_number"));
			product.setStatus(resultSet.getInt("status_id"));
			productList.add(product);
		}
		
		ps.close();
		return productList;
	}

	public Integer getPages(Filter filter) {
		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement(getFilteredCountStatement);
			
			if (filter.from != null) {
				ps.setTimestamp(1, filter.from);
			} else {
				ps.setNull(1, java.sql.Types.TIMESTAMP);
			}
			if (filter.to != null) {
				ps.setTimestamp(2, filter.to);
			} else {
				ps.setNull(2, java.sql.Types.TIMESTAMP);
			}
			if (filter.statusId != null) {
				ps.setInt(3, filter.statusId);
			} else {
				ps.setNull(3, java.sql.Types.INTEGER);
			}

			ResultSet res = ps.executeQuery();
			
			while (res.next()) {
				return ((res.getInt(1) - 1) / filter.perPage) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public Map<Integer, String> getNamesByProducts(List<Product> products) {
		try {
			Map<Integer, String> projectIdName = new HashMap<>();
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement(getNamesById);
			
			for (Product product : products) {
				ps.setInt(1, product.getId());
				ResultSet res = ps.executeQuery();
				while (res.next()) {
					projectIdName.put(product.getId(), res.getString(1));
				}
			}
			
			ps.close();
			return projectIdName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
