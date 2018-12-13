package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import orm.dto.DashboardDto;
import services.UserService;
import util.CurrentConnection;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService;
       
    /**
     * @throws SQLException  
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() 
    		throws ClassNotFoundException, SQLException {
        super();
        userService = UserService.getUserServiceInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try{
			DashboardDto dashboardDto;				
			dashboardDto = userService.getDashboard((String) 
					request.getSession().getAttribute("user"));
			request.setAttribute("dashboardDto", dashboardDto);			
		} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
		} finally {
			try {
				if(CurrentConnection.getCurrent() != null){
					CurrentConnection.getCurrent().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		
		request.getRequestDispatcher("/views/index.jsp").forward(request, response);
	}
}
