package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UserService;

public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() 
    		throws ClassNotFoundException, SQLException {
        super();
        userService = UserService.getUserServiceInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/passwordReset.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email");
		try {			
			userService.resetPassword(email);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/");
	}

}
