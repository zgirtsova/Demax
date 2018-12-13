package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orm.dto.NewProjectDto;
import orm.dto.ViewProjectDto;
import services.ProjectService;

public class ViewProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProjectService projectService;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ViewProjectServlet() 
    		throws ClassNotFoundException, SQLException {
        super();
        projectService = ProjectService.getProjectServiceInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {			
			ViewProjectDto projectDto = projectService
					.getProjectById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("projectDto", projectDto);
		} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/views/projects/viewProject.jsp").forward(request, response);
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
			projectService.updateProject(newProjectDto, 
					Integer.parseInt(request.getParameter("id")));
		} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/projects/all");
	}
}
