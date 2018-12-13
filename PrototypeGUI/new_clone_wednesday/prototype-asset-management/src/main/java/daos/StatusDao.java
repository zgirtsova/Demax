package daos;

import entities.Status;

public class StatusDao extends BaseDao<Status> {
    
    /**
     * insert method does not apply to StatusDao
     */
    @Override
    public boolean insert(Status obj) {
        return false;
    }

    /**
     * update method does not apply to StatusDao
     */
    @Override
    public boolean update(Status obj) {
        return false;
    }

    /**
     * delete method does not apply to StatusDao
     */
    @Override
    public boolean delete(int id) {
        return false;
    }
}
