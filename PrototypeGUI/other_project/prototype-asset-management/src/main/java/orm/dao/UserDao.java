package orm.dao;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import entities.User;
import util.CurrentConnection;
 
public class UserDao extends Dao<User> {
	private static UserDao userDao;
	
    private UserDao() throws SQLException, ClassNotFoundException {
        super();
    }
    
    public static final UserDao getUserDaoInstance() 
    		throws ClassNotFoundException, SQLException {
		if(userDao == null) {
			userDao = new UserDao();
		}
		
		return userDao;
	}

    public User getUserByEmail(String email) 
    		throws SQLException, ClassNotFoundException {
        if (email == null) { 
            return null;
        }
        
        PreparedStatement ps = CurrentConnection.getCurrent()
        		.prepareStatement("SELECT * FROM users WHERE email = ?");
        ps.setString(1, email);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setId(resultSet.getInt("id"));
            user.setLastName(resultSet.getString("last_name"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));

            return user;
        }
        return null;
    }
}