package daos;

import entities.User;

public class UserDao extends BaseDao<User> {
    
    /**
     * delete method does not apply to UserDao
     */
    @Override
    public boolean delete(int id) {
        return false;
    }

}
