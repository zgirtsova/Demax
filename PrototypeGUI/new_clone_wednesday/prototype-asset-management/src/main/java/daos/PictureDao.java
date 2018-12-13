package daos;

import entities.Picture;

import java.util.List;
import java.util.Map;

public class PictureDao extends AdvancedDao<Picture> {

    /**
     * update method does not apply to PictureDao
     */
    @Override
    public boolean update(Picture obj) {
        return false;
    }

    /**
     * getPerPage does not apply to PictureDao
     */
    @Override
    public Map.Entry<List<Picture>, Integer> getPerPage(int resultsPerPage, int page) {
        return null;
    }    

    public List<Picture> getPicturesByProductId(int id) {

        String ownerParam = "product_id";

       return super.getByOwnerId(ownerParam, id);
    }
}
