package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.ActionService;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ActionService actionService;

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() throws ClassNotFoundException, SQLException {
		super();
		actionService = ActionService.getActionServiceInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("user") != null) {
			try {
				actionService.registerAction(2, session.getAttribute("user").toString());

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			session.setAttribute("user", null);
			session.invalidate();
		}

		response.sendRedirect(request.getContextPath() + "/login");
	}

}
