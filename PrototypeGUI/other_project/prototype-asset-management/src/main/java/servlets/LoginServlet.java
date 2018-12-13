package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.UserService;
import util.CurrentConnection;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService;

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() 
			throws SQLException, ClassNotFoundException {
		super();
		userService = UserService.getUserServiceInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		/*
		User use = new User();
		use.setEmail("D.gospoginov@demax.bg");
		use.setFirstName("Dobromir");
		use.setLastName("Gospodinov");
		use.setPassword("12345678");
		use.setRole("Test user");
		try {
			userService.registerUser(use);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try{
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			HttpSession session = request.getSession();
			try {				
				if (userService.loginCheck(email, password)) {
					session.setAttribute("user", email);			
					response.sendRedirect(request.getContextPath() + "/");
				} else {
					response.sendRedirect(request.getContextPath() + "/login");
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if(CurrentConnection.getCurrent() != null){
					CurrentConnection.getCurrent().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
}
