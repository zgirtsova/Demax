package daos;

import entities.Product;
import helpers.FilterNames;
import singletons.ConnectionPoolSingleton;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class ProductDao extends AdvancedDao<Product> {

    public List<Product> getProductsByProjectId(int id) {

        String ownerParam = "project_id";

        return super.getByOwnerId(ownerParam, id);
    }
    
    public int getProductsInProgressCount() {

        String filterParam = "status_id";
        int inProgressStatusId = 2;

        return super.getCountByFilterParam(filterParam, inProgressStatusId);
    }

    public int getFinishedProductsCount() {

        String filterParam = "status_id";
        int finishedStatusId = 3;

        return super.getCountByFilterParam(filterParam, finishedStatusId);
    }

    public int getProductsByProjectIdCount(int id) {

        String filterParam = "project_id";

        return super.getCountByFilterParam(filterParam, id);
    }

    public int getProductId(String serialNumber) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String query = "SELECT id FROM products WHERE serial_number = ?";

        try {

            Connection connection = ConnectionPoolSingleton.getInstance().getCurrConn();

            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, serialNumber);

                if (preparedStatement != null) {
                    resultSet = preparedStatement.executeQuery();
                }

                if (resultSet != null) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return id;
    }
}
