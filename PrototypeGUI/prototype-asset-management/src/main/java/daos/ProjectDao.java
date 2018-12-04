package daos;

import entities.Project;

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
}
