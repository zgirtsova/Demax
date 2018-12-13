package orm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Action;
import util.CurrentConnection;

public class ActionDao extends Dao<Action> {
	private static ActionDao actionDao;
	private static final String query = "SELECT * FROM actions "
			+ "ORDER BY occured_on DESC LIMIT 15";	
	
	private ActionDao() throws SQLException, ClassNotFoundException {
		super();
	}
	
	public static final ActionDao getActionDaoInstance() 
			throws ClassNotFoundException, SQLException {
		if(actionDao == null) {
			actionDao = new ActionDao();
		}
		
		return actionDao;
	}

	public List<Action> getLastActions() 
			throws SQLException, ClassNotFoundException {
		List<Action> reply = new ArrayList<Action>();
		PreparedStatement ps = CurrentConnection.getCurrent()
				.prepareStatement(query);
		ResultSet res = ps.executeQuery();
		
		while (res.next()) {
			Action temp = new Action();
			temp.setId(res.getInt(1));
			temp.setDate(res.getTimestamp(2));
			temp.setActionId(res.getInt(3));
			temp.setUserEmail(res.getString(4));
			reply.add(temp);
		}

		res.close();
		ps.close();
		return reply;
	
		
	}
}
