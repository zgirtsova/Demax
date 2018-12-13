package orm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Product;
import util.CurrentConnection;
import util.Filter;

public class ProductDao extends Dao<Product>{
	private static ProductDao productDao;
	protected String getCountStatement;
	protected String getFinishedCountStatement;
	protected String filterStatement;
	protected String getAllStatement;
	protected HashMap<Byte, String> getAllStatements;
	protected String deleteStatement;
	protected String getFilteredCountStatement;

	private ProductDao() throws SQLException, ClassNotFoundException {
		super();
		getCountStatement = "SELECT COUNT(*) FROM products";
		getFinishedCountStatement = "SELECT COUNT(*) FROM products WHERE status_id = 3";
		deleteStatement = "DELETE * FROM products WHERE id = ?";
		getFilteredCountStatement = "WITH variables(date_from, date_to "
				+ ", statusId, projectId) as ( VALUES( CAST(? as Timestamp), "
				+ "CAST(? as Timestamp), CAST(? as Integer), CAST(? as Integer))) "
				+ "SELECT COUNT(*) FROM products,variables  "
				+ "WHERE (created_on >= variables.date_from OR variables.date_from IS NULL) "
				+ "AND (created_on <= variables.date_to OR variables.date_to IS NULL) "
				+ "AND (status_id = variables.statusId OR variables.statusId IS NULL) "
				+ "AND (project_id=variables.projectId OR variables.projectId IS NULL)";
		getAllStatements = new HashMap<>();
	}

	public static final ProductDao getProductDaoInstance() 
			throws ClassNotFoundException, SQLException {
		if (productDao == null) {
			productDao = new ProductDao();
		}

		return productDao;
	}

	public List<Product> getPerPage(Filter filter) 
			throws SQLException, ClassNotFoundException {
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
		CurrentConnection.getCurrent().beginRequest();
		System.out.println(CurrentConnection.getCurrent().getAutoCommit());
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(getAllStatements.get(key));
		ps.clearParameters();
		int count = 0;
		
		if (filter.from != null) {
			ps.setTimestamp(++count, filter.from);
		}
		if (filter.to != null) {
			ps.setTimestamp(++count, filter.to);
		}
		if (filter.statusId != null) {
			ps.setInt(++count, filter.statusId);
		}
		if (filter.projectId != null) {
			ps.setInt(++count, filter.projectId);
		}
		
		ps.setInt(++count, filter.perPage);
		ps.setInt(++count, ((filter.page - 1) * filter.perPage));
		List<Product> products = new ArrayList<Product>();
		ResultSet res = ps.executeQuery();
		CurrentConnection.getCurrent().endRequest();
		Product product = null;
		
		while (res.next()) {
			product = new Product();
			product.setId(res.getInt(1));
			product.setName(res.getString(2));
			product.setSerialNumber(res.getString(3));
			product.setQuantity(res.getInt(4));
			product.setDescription(res.getString(5));
			product.setProjectId(res.getInt(6));
			product.setStatus(res.getInt(7));
			product.setCreatedOn(res.getDate(8));
			products.add(product);
		}
		
		ps.close();
		return products;
	}

	protected synchronized String buildAllQuery(Filter filter) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM products WHERE 1 = 1 ");
		
		if (filter.from != null) {
			query.append(" AND created_on >= ? ");
		}
		if (filter.to != null) {
			query.append(" AND created_on <= ? ");
		}
		if (filter.statusId != null) {
			query.append(" AND status_id = ? ");
		}
		if (filter.projectId != null) {
			query.append(" AND project_id = ? ");
		}
		
		switch (filter.sortRule) {
			case 1:
				query.append("ORDER BY created_on ASC, quantity ASC");
				break;
			case 2:
				query.append("ORDER BY created_on ASC, quantity DESC");
				break;
			case 3:
				query.append("ORDER BY created_on DESC, quantity ASC");
				break;
			case 4:
				query.append("ORDER BY created_on DESC, quantity DESC");
				break;
			case 5:
				query.append("ORDER BY quantity ASC, created_on ASC");
				break;
			case 6:
				query.append("ORDER BY quantity ASC, created_on DESC");
				break;
			case 7:
				query.append("ORDER BY quantity DESC, created_on ASC");
				break;
			case 8:
				query.append("ORDER BY quantity DESC, created_on DESC");
				break;
		}
		
		query.append(" LIMIT ? OFFSET ?");
		return query.toString();
	}

	public Integer getPages(Filter filter) 
			throws ClassNotFoundException {
		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement(getFilteredCountStatement);
			ps.clearParameters();
			
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
			if (filter.projectId != null) {
				ps.setInt(4, filter.projectId);
			} else {
				ps.setNull(4, java.sql.Types.INTEGER);
			}

			ResultSet res = ps.executeQuery();
			
			while (res.next()) {
				Integer result = ((res.getInt(1) - 1) / filter.perPage) + 1;
				ps.close();
				return result;
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public Integer getCount(Filter filter) throws ClassNotFoundException {
		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement(getFilteredCountStatement);
			ps.clearParameters();
			
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
			if (filter.projectId != null) {
				ps.setInt(4, filter.projectId);
			} else {
				ps.setNull(4, java.sql.Types.INTEGER);
			}

			ResultSet res = ps.executeQuery();
			while (res.next()) {
				Integer out = res.getInt(1);
				ps.close();
				return out;
			}
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public Integer getFinishedCount() throws ClassNotFoundException {
		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement(getFinishedCountStatement);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				Integer result = resultSet.getInt(1);
				ps.close();
				return result;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer getInProgressCount() throws ClassNotFoundException {
		try {
			CurrentConnection.getCurrent().beginRequest();
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement("SELECT COUNT(*) FROM products WHERE status_id = 2");
			ResultSet resultSet = ps.executeQuery();

			CurrentConnection.getCurrent().endRequest();

			if (resultSet.next()) {
				return resultSet.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Product getProductBySerialNumber(String serialNumber) 
			throws ClassNotFoundException {
		if (serialNumber == null) {
			return null;
		}
		
		try {
			CurrentConnection.getCurrent().beginRequest();
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement("SELECT * FROM products WHERE serial_number = ?");
			ps.setString(1, serialNumber);
			ResultSet resultSet = ps.executeQuery();

			CurrentConnection.getCurrent().endRequest();

			if (resultSet.next()) {
				Product product = new Product();
				product.setCreatedOn(resultSet.getDate("created_on"));
				product.setDescription(resultSet.getString("description"));
				product.setId(resultSet.getInt("id"));
				;
				product.setName(resultSet.getString("product_name"));
				product.setProjectId(resultSet.getInt("project_id"));
				product.setQuantity(resultSet.getInt("quantity"));
				product.setSerialNumber(resultSet.getString("serial_number"));
				product.setStatus(resultSet.getInt("status_id"));

				return product;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Integer> getByProjectId(int id) 
			throws SQLException, ClassNotFoundException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement("SELECT id FROM products WHERE project_id = ?");
		ps.setInt(1, id);
		List<Integer> productIds = new ArrayList<>();
		ResultSet res = ps.executeQuery();
		
		while (res.next()) {
			productIds.add(res.getInt(1));
		}
		return productIds;
	}

	public Product getBySerialNumber(String sn) 
			throws SQLException, ClassNotFoundException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement("SELECT * FROM products WHERE serial_number = ?");
		ps.setString(1, sn);
		ResultSet res = ps.executeQuery();
		
		while (res.next()) {
			Product product = new Product();
			product.setId(res.getInt("id"));
			product.setName(res.getString("product_name"));
			product.setSerialNumber(res.getString("serial_number"));
			product.setQuantity(res.getInt("quantity"));
			product.setDescription(res.getString("description"));
			product.setProjectId(res.getInt("project_id"));
			product.setStatus(res.getInt("status_id"));
			product.setCreatedOn(res.getTimestamp("created_on"));
			return product;
		}
		return null;
	}
}
