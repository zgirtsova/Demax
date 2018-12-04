package dtos.dashboards;

import java.util.List;

import dtos.actions.ActionDTO;

public class DashboardDTO {
    private int newProjectsCount;
    private int finishedProjectsCount;
    private int productsInProgressCount;
    private int finishedProductsCount;
    private List<ActionDTO> lastFifteenActions;

    public DashboardDTO(int newProjectsCount, int finishedProjectsCount, int productsInProgressCount,
            int finishedProductsCount, List<ActionDTO> lastFifteenActions) {
        this.newProjectsCount = newProjectsCount;
        this.finishedProjectsCount = finishedProjectsCount;
        this.productsInProgressCount = productsInProgressCount;
        this.finishedProductsCount = finishedProductsCount;
        this.lastFifteenActions = lastFifteenActions;
    }

    public int getNewProjectsCount() {
        return newProjectsCount;
    }

    private void setNewProjectsCount(int newProjectsCount) {
        this.newProjectsCount = newProjectsCount;
    }

    public int getFinishedProjectsCount() {
        return finishedProjectsCount;
    }

    private void setFinishedProjectsCount(int finishedProjectsCount) {
        this.finishedProjectsCount = finishedProjectsCount;
    }

    public int getProductsInProgressCount() {
        return productsInProgressCount;
    }

    private void setProductsInProgressCount(int productsInProgressCount) {
        this.productsInProgressCount = productsInProgressCount;
    }

    public int getFinishedProductsCount() {
        return finishedProductsCount;
    }

    private void setFinishedProductsCount(int finishedProductsCount) {
        this.finishedProductsCount = finishedProductsCount;
    }

    public List<ActionDTO> getLastFifteenActions() {
        return lastFifteenActions;
    }

    private void setLastFifteenActions(List<ActionDTO> lastFifteenActions) {
        this.lastFifteenActions = lastFifteenActions;
    }

}