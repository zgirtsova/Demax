package services;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import entities.Action;
import orm.dao.Dao;
import util.CurrentConnection;

public class ActionService {
	private static ActionService actionService;
	private ActionService() throws ClassNotFoundException, SQLException {
		
	}

	public static final ActionService getActionServiceInstance() throws ClassNotFoundException, SQLException {
		if (actionService == null) {
			actionService = new ActionService();
		}

		return actionService;
	}

	public void registerAction(Integer actionId, String userEmail) throws SQLException, ClassNotFoundException {
		CurrentConnection.beginRequest();
		try{
			Dao<Action> dao = new Dao<>(Action.class);
			Action temp = new Action();
			temp.setDate(Timestamp.valueOf(LocalDateTime.now()));
			temp.setUserEmail(userEmail);
			temp.setActionId(actionId);
			dao.insert(temp);
			CurrentConnection.commit();
		}catch (SQLException e) {
		  e.printStackTrace();
		  CurrentConnection.rollback();
		  throw e;
		}
	}
}