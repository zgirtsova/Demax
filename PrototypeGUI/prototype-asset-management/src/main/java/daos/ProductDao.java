package daos;

import entities.Product;

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
        int finishedStatisId = 3;

        return super.getCountByFilterParam(filterParam, finishedStatisId);
    }

    public int getProductsByProjectIdCount(int id) {

        String filterParam = "project_id";

        return super.getCountByFilterParam(filterParam, id);
    }
}
