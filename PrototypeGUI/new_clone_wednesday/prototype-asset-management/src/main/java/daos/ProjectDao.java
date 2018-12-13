package daos;

import entities.Project;
import singletons.ConnectionPoolSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDao extends AdvancedDao<Project> {

    public int getNewProjectsCount() {

        String filterParam = "status_id";
        int newStatusId = 1;

        return super.getCountByFilterParam(filterParam, newStatusId);
    }
    
    public int getFinishedProjectsCount() {

        String filterParam = "status_id";
        int finishedStatusId = 3;

        return super.getCountByFilterParam(filterParam, finishedStatusId);
    }

    public String getProjectName(int projectId) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String projectName = "";

        String query = "SELECT project_name FROM projects WHERE id = ?";

        try {

            Connection connection = ConnectionPoolSingleton.getInstance().getCurrConn();

            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, projectId);

                if (preparedStatement != null) {
                    resultSet = preparedStatement.executeQuery();
                }

                if (resultSet != null) {
                    if (resultSet.next()) {
                        projectName = resultSet.getString("project_name");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return projectName;
    }

}
