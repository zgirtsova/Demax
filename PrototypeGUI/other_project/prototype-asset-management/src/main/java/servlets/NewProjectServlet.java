package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orm.dto.NewProjectDto;
import services.ProjectService;

public class NewProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProjectService projectService;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public NewProjectServlet() 
    		throws ClassNotFoundException, SQLException {
        super();
        projectService = ProjectService.getProjectServiceInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/projects/newProject.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		NewProjectDto newProjectDto = new NewProjectDto();
		newProjectDto.setCompanyName(request.getParameter("companyName"));
		newProjectDto.setProjectManager(request.getParameter("projectManager"));
		newProjectDto.setProjectName(request.getParameter("projectName"));
		newProjectDto.setStatus(request.getParameter("status"));
		
		try {			
			projectService.createProject(newProjectDto, 
					request.getSession().getAttribute("user").toString());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/projects/all");
	}

}
