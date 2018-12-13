package services;

import daos.ProductDao;
import daos.ProjectDao;
import dtos.dashboards.DashboardDTO;
import singletons.ConnectionPoolSingleton;

public class DashboardService {

    private ProjectDao projectDao;
    private ProductDao productDao;
    private ActionService actionService;

    public DashboardService() {
        this.projectDao = new ProjectDao();
        this.productDao = new ProductDao();
        this.actionService = new ActionService();
    }

    public DashboardDTO getDashboardDTO(int userId) {

        DashboardDTO dashboardDTO = new DashboardDTO();

            //BEGIN
            ConnectionPoolSingleton.getInstance().begin();

        try {
            dashboardDTO.setNewProjectsCount(projectDao.getNewProjectsCount());
            dashboardDTO.setFinishedProjectsCount(projectDao.getFinishedProjectsCount());
            dashboardDTO.setProductsInProgressCount(productDao.getProductsInProgressCount());
            dashboardDTO.setFinishedProductsCount(productDao.getFinishedProductsCount());
            dashboardDTO.setLastFifteenActions(actionService.getActions(userId));

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

        return dashboardDTO;
    }
}