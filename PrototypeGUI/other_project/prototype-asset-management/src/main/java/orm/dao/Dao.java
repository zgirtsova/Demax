package orm.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import util.CurrentConnection;
import util.EntityFieldData;

public class Dao<T> {
	protected Class<T> classType;
	protected String tableName;
	protected String className;
	protected HashMap<String, Method> methods;
	protected ResultSet tableMetaData;
	protected String updateStatement;
	protected String getByIdStatement;
	protected String deleteStatement;
	protected String insertStatement;

	public Dao(Class<T> clazz) throws ClassNotFoundException, SQLException{
		this.construct(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public Dao() throws SQLException, ClassNotFoundException {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) type;
		classType = (Class<T>) (paramType.getActualTypeArguments()[0]);
		this.construct(classType);
	}
	
	public T getById(int id) throws SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(getByIdStatement);
		ps.setObject(1, id);
		ResultSet res = ps.executeQuery();
		ResultSetMetaData rsmd = res.getMetaData();
		T entity = null;
		
		try {
			entity = (T) Class.forName(className)
					.getConstructor(null).newInstance(null);
			int count = 1;
			
			while (res.next()) {
				while (count <= (methods.size() / 2)) {
					Method temp = methods.get("set_" + rsmd.getColumnName(count));
					temp.invoke(entity, res.getObject(count));
					count++;
				}
			}
		} catch (IllegalArgumentException | SecurityException | IllegalAccessException | InstantiationException
				| InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		ps.close();
		return entity;
	}

	public Integer update(T entity) throws SQLException {
		tableMetaData = CurrentConnection.getCurrent()
				.getMetaData().getColumns(null, null, tableName, null);
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(updateStatement);
		int count = 0;
		tableMetaData.next();
		
		try {
			while (tableMetaData.next()) {
				Object t = methods.get("get_" + tableMetaData
						.getString("COLUMN_NAME")).invoke(entity);
				
				if (t == null) {
				} else if (methods.get("get_" + tableMetaData.getString("COLUMN_NAME"))
						.getAnnotation(EntityFieldData.class).type() == File.class) {
					FileInputStream s;
					
					try {
						s = new FileInputStream((File) t);
						ps.setBinaryStream(++count, s, ((File) t).length());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					ps.setObject(++count, t, methods
							.get("get_" + tableMetaData.getString("COLUMN_NAME"))
							.getAnnotation(EntityFieldData.class).sqlType());
				}
			}
			
			ps.setObject(++count, (entity.getClass().getMethod("getId")
					.invoke(entity)), java.sql.Types.INTEGER);
		} catch (IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
		
		Integer out = ps.executeUpdate();
		ps.close();
		return out;
	}

	public Integer insert(T entity) throws SQLException {
		tableMetaData = CurrentConnection.getCurrent()
				.getMetaData().getColumns(null, null, tableName, null);
		
		try {
			PreparedStatement ps = CurrentConnection.getCurrent()
					.prepareStatement(insertStatement);
			int count = 0;
			tableMetaData.next();
			
			while (tableMetaData.next()) {
				Object t = methods.get("get_" + tableMetaData
						.getString("COLUMN_NAME")).invoke(entity);

				if (t != null) {
						ps.setObject(++count, t, methods
								.get("get_" + tableMetaData.getString("COLUMN_NAME"))
										.getAnnotation(EntityFieldData.class).sqlType());
				}
			}
			
			Integer out = ps.executeUpdate();
			ps.close();
			return out;
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int delete(int id) throws SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(deleteStatement);
		ps.clearParameters();
		ps.setObject(1, id);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}

	private void construct(Class<T> classType) 
			throws SQLException,ClassNotFoundException {
		tableName = classType.toString().substring(15).toLowerCase() + "s";
		className = classType.toString().substring(6);
		tableMetaData = CurrentConnection.getCurrent().getMetaData()
				.getColumns(null, null, tableName, null);
		Method[] declaredMethods = Class.forName(className).getDeclaredMethods();
		methods = new HashMap<String, Method>();
		StringBuilder query = new StringBuilder();
		int n = 0;
		
		for (int j = 0; j < declaredMethods.length; j++) {
			try {
				methods.put(declaredMethods[j].getAnnotation(EntityFieldData.class)
						.columnName(), declaredMethods[j]);
			} catch (NullPointerException e) {}
		}
		
		deleteStatement = "DELETE FROM " + tableName + " WHERE id = ?;";		
		getByIdStatement = "SELECT * FROM " + tableName + " WHERE id = ?;";		
		tableMetaData = CurrentConnection.getCurrent().getMetaData()
				.getColumns(null, null, tableName, null);
		tableMetaData.next();
		query.append("UPDATE " + tableName + " SET");
		
		while (tableMetaData.next()) {
			query.append(" " + tableMetaData.getString("COLUMN_NAME") + " = ?,");
		}
		
		query.deleteCharAt(query.length() - 1);
		query.append(" WHERE id=?");
		updateStatement = query.toString();
		
		query = new StringBuilder();
		tableMetaData = CurrentConnection.getCurrent().getMetaData()
				.getColumns(null, null, tableName, null);
		query.append("INSERT INTO " + tableName + "(");
		tableMetaData.next();
		
		while (tableMetaData.next()) {
			query.append(" " + tableMetaData.getString("COLUMN_NAME") + ",");
			n++;
		}
		
		query.deleteCharAt(query.length() - 1);
		query.append(") VALUES(");
		
		for (int i = 0; i < n; i++) {
			query.append("?,");
		}
		
		query.deleteCharAt(query.length() - 1);
		query.append(")");
		insertStatement = query.toString();
	}
}
