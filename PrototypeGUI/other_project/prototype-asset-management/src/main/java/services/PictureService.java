package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;
import entities.Picture;
import orm.dao.PictureDao;
import orm.dto.PictureDto;
import util.CurrentConnection;

public class PictureService {
	private static PictureService pictureService;
	private static PictureDao pictureDao;

	private PictureService() throws ClassNotFoundException, SQLException {
		pictureDao = PictureDao.getPictureDaoInstance();
	}

	public static final PictureService getPictureServiceInstance() throws ClassNotFoundException, SQLException {
		if (pictureService == null) {
			pictureService = new PictureService();
		}

		return pictureService;
	}

	public void createPicture(PictureDto pictureDto) throws IOException, SQLException, ClassNotFoundException {
		Picture picture = new Picture();
		picture.setId(DigestUtils.sha256Hex(pictureDto.getPicture()));
		picture.setProductId(pictureDto.getProductId());
		CurrentConnection.beginRequest();
		try {
			if (pictureDao.getById(DigestUtils.sha256Hex(pictureDto.getPicture())) != null) {
				pictureDao.insertProductPicture(picture);
				System.out.println("adaaaaaaaaaaaaaa");
				CurrentConnection.commit();
				return;
			}

			picture.setPicture(Base64.getDecoder().decode(pictureDto.getPicture()));
			pictureDao.insert(picture);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public void deletePicture(String pictureId, Integer productId) throws SQLException, ClassNotFoundException {
		CurrentConnection.beginRequest();
		try {
			pictureDao.delete(pictureId, productId);
			CurrentConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}
}
