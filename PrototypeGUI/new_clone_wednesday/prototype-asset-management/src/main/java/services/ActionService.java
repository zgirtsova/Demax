package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import daos.ActionDao;
import dtos.actions.ActionDTO;
import entities.Action;
import helpers.ModelMapper;
import singletons.ConnectionPoolSingleton;

public class ActionService {

    private ActionDao actionDao;
    private ModelMapper modelMapper;

    public ActionService() {
        this.actionDao = new ActionDao();
    }

    public List<ActionDTO> getActions(int userId) {

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();
        List<ActionDTO> actionDTOs = new ArrayList<>();

        try {
            List<Action> actions = actionDao.getLastActionsByUserId(userId);
            modelMapper = new ModelMapper();

            if (actions != null) {
                for (Action action : actions) {
                    ActionDTO actionDTO = (ActionDTO) modelMapper.map(action, ActionDTO.class);

                    actionDTOs.add(actionDTO);
                }
            }

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

        return actionDTOs;
    }
}