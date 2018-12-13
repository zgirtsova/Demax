package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.codec.digest.DigestUtils;

import entities.Action;
import entities.User;
import orm.dao.ActionDao;
import orm.dao.Dao;
import orm.dao.ProductDao;
import orm.dao.ProjectDao;
import orm.dao.UserDao;
import orm.dto.ActionDto;
import orm.dto.DashboardDto;
import orm.dto.UserDto;
import util.CurrentConnection;
import util.PossibleAction;

public class UserService {
	private final char[] chars = "absdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*()_+-=[]|,./?><"
			.toCharArray();
	private static UserService userService;
	private static ActionService actionService;
	private static UserDao userDao;
	private static ActionDao actionDao;
	private static ProductDao productDao;
	private static ProjectDao projectDao;
	private static Dao<User> dao;

	private UserService() throws ClassNotFoundException, SQLException {
		userDao = UserDao.getUserDaoInstance();
		actionDao = ActionDao.getActionDaoInstance();
		productDao = ProductDao.getProductDaoInstance();
		projectDao = ProjectDao.getProjectDaoInstance();
		dao = new Dao<>(User.class);
		actionService = ActionService.getActionServiceInstance();
	}

	public static final UserService getUserServiceInstance() 
			throws ClassNotFoundException, SQLException {
		if (userService == null) {
			userService = new UserService();
		}

		return userService;
	}

	public void resetPassword(String email) 
			throws SQLException, ClassNotFoundException {
		String newPassword = generatePassword();
		String to = email;
		String from = "gi.georgiev@demax.bg";
		String host = "smtp.mailtrap.io";
		String username = "43e130712252c1";
		String password = "13f6af16c6d9a8";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, 
					new InternetAddress(to));
			message.setSubject("Password reset");
			message.setText("Here is your new password - " + newPassword);

			Transport.send(message, username, password);
			User user = getUserByEmail(email);
			user.setPassword(DigestUtils.sha256Hex(newPassword));
			CurrentConnection.beginRequest();		
			try {
				dao.update(user);
				CurrentConnection.commit();
			}catch (SQLException e) {
				e.printStackTrace();
				CurrentConnection.rollback();
				throw e;
			}
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public User getUserByEmail(String email) 
			throws SQLException, ClassNotFoundException {
		User user;
		CurrentConnection.beginRequest();		
		try {
			user = userDao.getUserByEmail(email);
			CurrentConnection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
		
		return user;
	}

	public boolean loginCheck(String email, String password) 
			throws SQLException, ClassNotFoundException {
		User user;
		CurrentConnection.beginRequest();
		try {
			user = userDao.getUserByEmail(email);
			if (user == null)
				return false;
			actionService.registerAction(1, email);
			CurrentConnection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}

		return user.getPassword().equals(DigestUtils
				.sha256Hex(password));
	}

	public DashboardDto getDashboard(String email) 
			throws SQLException, ClassNotFoundException {
		DashboardDto dashboardDto = new DashboardDto();
		List<Action> actions;
		CurrentConnection.beginRequest();
		try {
			dashboardDto.setFinishedProductsCount(productDao
					.getFinishedCount());
			dashboardDto.setFinishedProjectsCount(projectDao
					.getFinishedCount());
			dashboardDto.setLoggedInUserFirstName(userDao
					.getUserByEmail(email).getFirstName());
			dashboardDto.setNewProjectsCount(projectDao
					.getNewCount());
			dashboardDto.setProductsInProgressCount(productDao
					.getInProgressCount());
			actions = actionDao.getLastActions();
			CurrentConnection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
		List<ActionDto> actionDtos = new ArrayList<>();
		
		for (Action action : actions) {
			if (!action.getUserEmail().equals(email)) {
				continue;
			}
			ActionDto actionDto = new ActionDto();
			actionDto.setId(action.getId());
			actionDto.setAction(PossibleAction
					.getActionById(action.getActionId()));
			actionDto.setDate(action.getDate());
			actionDto.setUser(action.getUserEmail());
			actionDtos.add(actionDto);
		}
		
		dashboardDto.setLastFifteenActions(actionDtos);

		return dashboardDto;
	}

	public void registerUser(User user) 
			throws SQLException, ClassNotFoundException {
		user.setPassword(DigestUtils.sha256Hex(user
				.getPassword()));
		CurrentConnection.beginRequest();
		try {
			dao.insert(user);
			CurrentConnection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	public UserDto getUserInfo(String email) 
			throws SQLException, ClassNotFoundException {
		User user;
		CurrentConnection.beginRequest();
		try {
			user = getUserByEmail(email);
			CurrentConnection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
		UserDto userDto = new UserDto();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setRole(user.getRole());

		return userDto;
	}

	public void updatePassword(String email, String password, String oldPassword)
			throws SQLException, ClassNotFoundException {
		User user;
		CurrentConnection.beginRequest();
		try {
			user = getUserByEmail(email);
			if (user.getPassword().equals(oldPassword)) {
				user.setPassword(password);
				dao.update(user);
			}
			CurrentConnection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			CurrentConnection.rollback();
			throw e;
		}
	}

	private String generatePassword() {
		StringBuilder sb = new StringBuilder();
		int n = 8;
		Random rand = new Random();

		for (int i = 0; i < n; i++) {
			int k = rand.nextInt(chars.length);
			sb.append(chars[k]);
		}
		return sb.toString();
	}
}
