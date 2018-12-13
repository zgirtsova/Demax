package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import orm.dto.UserDto;
import services.UserService;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() 
    		throws ClassNotFoundException, SQLException {
        super();
        userService = UserService.getUserServiceInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDto userDto;
		try {			
			userDto = userService.getUserInfo((String) 
					request.getSession().getAttribute("user"));
			request.setAttribute("userDto", userDto);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String newPassword2 = req.getParameter("newPassword2");
		
		try {
			if(newPassword.equals(newPassword2)	&& newPassword.length() >= 8) {
				userService.updatePassword((String) req.getSession().getAttribute("user"), 
						DigestUtils.sha256Hex(newPassword), DigestUtils.sha256Hex(oldPassword));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/");
	}

}
