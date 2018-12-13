package daos;

import java.util.List;

import entities.Action;

public class ActionDao extends AdvancedDao<Action> {

    public List<Action> getLastActionsByUserId(int id) {

        String ownerParam = "user_id";
        String limitQuery = "ORDER BY occured_on DESC LIMIT 15";

        return super.getByOwnerId(ownerParam, id, limitQuery);
    }

}
