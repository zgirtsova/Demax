package dtos.dashboards;

import java.util.List;

import dtos.actions.ActionDTO;

public class DashboardDTO {

    private int newProjectsCount;
    private int finishedProjectsCount;
    private int productsInProgressCount;
    private int finishedProductsCount;
    private List<ActionDTO> lastFifteenActions;

    public DashboardDTO() {}

    public int getNewProjectsCount() {
        return newProjectsCount;
    }

    public void setNewProjectsCount(int newProjectsCount) {
        this.newProjectsCount = newProjectsCount;
    }

    public int getFinishedProjectsCount() {
        return finishedProjectsCount;
    }

    public void setFinishedProjectsCount(int finishedProjectsCount) {
        this.finishedProjectsCount = finishedProjectsCount;
    }

    public int getProductsInProgressCount() {
        return productsInProgressCount;
    }

    public void setProductsInProgressCount(int productsInProgressCount) {
        this.productsInProgressCount = productsInProgressCount;
    }

    public int getFinishedProductsCount() {
        return finishedProductsCount;
    }

    public void setFinishedProductsCount(int finishedProductsCount) {
        this.finishedProductsCount = finishedProductsCount;
    }

    public List<ActionDTO> getLastFifteenActions() {
        return lastFifteenActions;
    }

    public void setLastFifteenActions(List<ActionDTO> lastFifteenActions) {
        this.lastFifteenActions = lastFifteenActions;
    }

}