package services;

import daos.PictureDao;
import dtos.pictures.PictureDTO;
import dtos.pictures.PicturePerProductDTO;
import entities.Picture;
import helpers.ModelMapper;
import singletons.ConnectionPoolSingleton;

import java.util.List;

public class PictureService {

    private PictureDao pictureDao;
    private ModelMapper modelMapper;

    public PictureService() {
        pictureDao =new PictureDao();
        modelMapper = new ModelMapper();
    }

    public void addPicture(PictureDTO pictureDTO) {

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            Picture picture = (Picture) modelMapper.map(pictureDTO, Picture.class);

            pictureDao.insert(picture);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

    }

    public PicturePerProductDTO getPicturePerProduct(int productId) {

        PicturePerProductDTO picturePerProductDTO = new PicturePerProductDTO();

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();

        try {
            List<Picture> picturesPerProduct = pictureDao.getPicturesByProductId(productId);

            if (picturesPerProduct.size() != 0) {
                picturePerProductDTO.setPicture(picturesPerProduct.get(0).getPicture());
            }
            picturePerProductDTO.setPicturesCount(picturesPerProduct.size());

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

        return picturePerProductDTO;
    }
}
