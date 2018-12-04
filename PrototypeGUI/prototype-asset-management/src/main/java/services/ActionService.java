package services;

import java.util.List;
import java.util.stream.Collectors;

import daos.ActionDao;
import dtos.actions.ActionDTO;
import entities.Action;

public class ActionService {

    private ActionDao actionDao;

    public ActionService() {
        this.actionDao = new ActionDao();
    }

    public List<ActionDTO> getActions(int userId) {

        List<Action> actions = actionDao.getLastActionsByUserId(userId);
        List<ActionDTO> actionDTOs = null;

        if (actions != null) {
            actionDTOs = actions.stream()
                .map(a -> new ActionDTO(a.getId(), a.getOccuredOn(), a.getUserId(), a.getAction()))
                .collect(Collectors.toList());
        }

        return actionDTOs;
    }
}