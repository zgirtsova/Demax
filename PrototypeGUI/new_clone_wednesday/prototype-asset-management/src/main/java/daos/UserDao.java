package daos;

import entities.User;
import singletons.ConnectionPoolSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDao<User> {
    
    /**
     * delete method does not apply to UserDao
     */
    @Override
    public boolean delete(int id) {
        return false;
    }

    public User getByEmailAndPassword(String email, String password) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        User user = null;

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {

            Connection connection = ConnectionPoolSingleton.getInstance().getCurrConn();

            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                if (preparedStatement != null) {
                    resultSet = preparedStatement.executeQuery();
                }

                if (resultSet != null) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        user = super.getById(id);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return user;
    }

}
