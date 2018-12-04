package services;

import java.util.List;

import daos.ProductDao;
import daos.ProjectDao;
import dtos.dashboards.DashboardDTO;
import dtos.actions.ActionDTO;

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

        int newProjectsCount = projectDao.getNewProjectsCount();
        int finishedProjectsCount = projectDao.getFinishedProjectsCount();
        int productsInProgressCount = productDao.getProductsInProgressCount();
        int finishedProductsCount = productDao.getFinishedProductsCount();
        List<ActionDTO> lastFifteenActions = actionService.getActions(userId);

        return new DashboardDTO(newProjectsCount, finishedProjectsCount, productsInProgressCount, finishedProductsCount, lastFifteenActions);
    }
}