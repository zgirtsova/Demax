package orm.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Picture;
import entities.Product;
import util.CurrentConnection;

public class PictureDao extends Dao<Picture> {
	private static PictureDao pictureDao;

	private String getPictureByProdIdStatement;
	private String getPicturesCountStatement;
	private String getPictureByIdStatement;
	private String insertPicture;
	private String insertProductPicture;
	private String deletePictureProductRelation;
	private String isAttatched;
	private String getRowExistsStatus;
	private String deletePicture;

	private PictureDao() throws SQLException, ClassNotFoundException {
		super();
		getPictureByProdIdStatement = "SELECT * FROM pictures INNER JOIN products_pictures "
				+ "ON pictures.id = products_pictures.picture_id "
				+ "WHERE products_pictures.product_id = ?";
		getPicturesCountStatement = "SELECT product_id,COUNT(id) FROM pictures "
				+ "INNER JOIN products_pictures ON products_pictures.picture_id = pictures.id "
				+ "WHERE product_id = ? GROUP BY product_id";
		getPictureByIdStatement = "SELECT * FROM pictures WHERE id = ?";
		insertPicture = "INSERT INTO pictures VALUES(?, ?)";
		insertProductPicture = "INSERT INTO products_pictures VALUES(?, ?)";
		deletePictureProductRelation = "DELETE FROM products_pictures "
				+ "WHERE picture_id = ? AND product_id = ?";
		isAttatched = "SELECT * FROM products_pictures WHERE picture_id = ?";
		deletePicture = "DELETE FROM pictures WHERE id = ?";
		getRowExistsStatus = "SELECT * FROM products_pictures "
				+ "WHERE picture_id = ? AND product_id = ?";
	}

	public static final PictureDao getPictureDaoInstance() 
			throws ClassNotFoundException, SQLException {
		if (pictureDao == null) {
			pictureDao = new PictureDao();
		}

		return pictureDao;
	}

	public Picture getById(String id) 
			throws ClassNotFoundException, SQLException, IOException {
		//if(true)throw new SQLException("DOBY OVERFLOW EXCEPTION");
				PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(getPictureByIdStatement);
		ps.setString(1, id);
		ResultSet resultSet = ps.executeQuery();
		
		if (resultSet.next()) {
			Picture picture = new Picture();
			picture.setId(resultSet.getString("id"));
			picture.setPicture(resultSet.getBytes("picture"));
			ps.close();
			return picture;
		}
		
		ps.close();
		return null;
	}

	@Override
	public Integer insert(Picture picture) 
			throws SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(insertPicture);
		ps.setString(2, picture.getId());
		ps.setBytes(1, picture.getPicture());
		ps.execute();
		insertProductPicture(picture);
		ps.close();
		return 0;
	}

	public void delete(String pictureId, Integer productId) 
			throws SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(deletePictureProductRelation);
		ps.setString(1, pictureId);
		ps.setInt(2, productId);
		ps.execute();
		ps.close();
		checkPicturesInProductsPictures(pictureId);
	}

	public void checkPicturesInProductsPictures(String pictureId) 
			throws SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(isAttatched);
		PreparedStatement psDelete = CurrentConnection.getCurrent()
				.prepareStatement(deletePicture);
		ps.setString(1, pictureId);
		ResultSet resultSet = ps.executeQuery();
		
		if (!resultSet.next()) {
			psDelete.clearParameters();
			psDelete.setString(1, pictureId);
			psDelete.execute();
		}
		
		ps.close();
		psDelete.close();
	}

	public void insertProductPicture(Picture picture) 
			throws SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(getRowExistsStatus);
		PreparedStatement psInsert = CurrentConnection.getCurrent()
				.prepareStatement(insertProductPicture);
		ps.setString(1, picture.getId());
		ps.setInt(2, picture.getProductId());
		ResultSet resultSet = ps.executeQuery();
		
		if (resultSet.next()) {
			return;
		}
		
		psInsert.setInt(1, picture.getProductId());
		psInsert.setString(2, picture.getId());
		psInsert.execute();
		ps.close();
		psInsert.close();
	}

	public List<Picture> getPicturesByProductId(int id) 
			throws IOException, SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(getPictureByProdIdStatement);
		try {
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			List<Picture> pictures = new ArrayList<>();

			while (resultSet.next()) {
				Picture picture = new Picture();
				picture.setId(resultSet.getString("id"));
				picture.setPicture(resultSet.getBytes("picture"));
				picture.setProductId(resultSet.getInt("product_id"));
				pictures.add(picture);
			}
			
			ps.close();
			return pictures;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ps.close();
		return null;
	}

	public Map<Integer, Integer> getCountByProducts(List<Product> prods) 
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(getPicturesCountStatement);
		try {
			Map<Integer, Integer> out = new HashMap<>();
			
			for (Product prod : prods) {
				ps.clearParameters();
				ps.setInt(1, prod.getId());
				ResultSet res = ps.executeQuery();
				
				while (res.next()) {
					out.put(res.getInt(1), res.getInt(2));
				}
			}

			return out;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ps.close();
		return null;
	}

	public Map<Integer, Picture> getPicturesByProducts(List<Product> p) 
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(getPictureByProdIdStatement);
		try {
			Map<Integer, Picture> out = new HashMap<>();
			
			for (Product prod : p) {
				ps.clearParameters();
				ps.setInt(1, prod.getId());
				ResultSet res = ps.executeQuery();

				while (res.next()) {
					Picture picture = new Picture();
					picture.setId(res.getString("id"));
					picture.setPicture(res.getBytes("picture"));
					picture.setProductId(res.getInt("product_id"));
					out.put(res.getInt(3), picture);
				}
			}

			return out;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		ps.close();
		return null;
	}
}
