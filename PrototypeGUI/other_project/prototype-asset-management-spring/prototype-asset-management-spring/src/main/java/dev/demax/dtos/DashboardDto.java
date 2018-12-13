package dev.demax.dtos;

import java.util.Set;

public class DashboardDto {
	private Integer newProjectsCount;
	private Integer finishedProjectsCount;
	private Integer productsInProgressCount;
	private Integer finishedProductsCount;
	private String loggedInUserFirstName;
	private Set<ActionDto> lastFifteenActions;
	
	public DashboardDto() {}
	 
	public Integer getNewProjectsCount() {
		return newProjectsCount;
	}
	 
	public void setNewProjectsCount(Integer newProjectsCount) {
		this.newProjectsCount = newProjectsCount;
	}
	 
	public Integer getFinishedProjectsCount() {
		return finishedProjectsCount;
	}
	 
	public void setFinishedProjectsCount(Integer finishedProjectsCount) {
		this.finishedProjectsCount = finishedProjectsCount;
	}
	 
	public Integer getProductsInProgressCount() {
		return productsInProgressCount;
	}
	 
	public void setProductsInProgressCount(Integer productsInProgressCount) {
		this.productsInProgressCount = productsInProgressCount;
	}
	 
	public Integer getFinishedProductsCount() {
		return finishedProductsCount;
	}
	 
	public void setFinishedProductsCount(Integer finishedProductsCount) {
		this.finishedProductsCount = finishedProductsCount;
	}
	 
	public String getLoggedInUserFirstName() {
		return loggedInUserFirstName;
	}
	 
	public void setLoggedInUserFirstName(String loggedInUserFirstName) {
		this.loggedInUserFirstName = loggedInUserFirstName;
	}
	 
	public Set<ActionDto> getLastFifteenActions() {
		return lastFifteenActions;
	}
	 
	public void setLastFifteenActions(Set<ActionDto> lastFifteenActions) {
		this.lastFifteenActions = lastFifteenActions;
	}
}
