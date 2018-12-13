package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orm.dto.ProjectDto;
import services.ProjectService;
import util.CurrentConnection;
import util.Filter;
import util.Status;

public class AllProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProjectService projectService;

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see HttpServlet#HttpServlet()
	 */
	public AllProjectsServlet() 
			throws ClassNotFoundException, SQLException {
		super();
		projectService = ProjectService
				.getProjectServiceInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateFrom = request.getParameter("dateFrom");
			String dateTo = request.getParameter("dateTo");
			String status = request.getParameter("status");
			Timestamp dateF = null;
			Timestamp dateT = null;
			Integer page = null;
			Integer numberOfResults = null;
			Integer statusId = null;
			Object[] result;
			
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {}
			
			try {
				numberOfResults = Integer.parseInt(request.getParameter("numPerPage"));
			} catch (NumberFormatException e) {}
			
			if (request.getParameter("sort1") != null && !request
					.getParameter("sort1").equals("null")
					&& !request.getParameter("sort1").isEmpty()) {
			}
			
			if (page == null || page < 1) {
				page = 1;
			}
			
			if (numberOfResults == null || numberOfResults < 1) {
				numberOfResults = 5;
			}

			if (status != null && !status.equals("All") && 
					!status.equals("null")) {
				statusId = Status.getStatusIdByName(status);
			}

			try {
				if (dateFrom != null && !dateFrom.equals("null") && 
						!dateFrom.isEmpty()) {

					dateF = new Timestamp(sdf.parse(dateFrom).getTime());
				}
				if (dateTo != null && !dateTo.equals("null") && 
						!dateTo.isEmpty()) {
					dateT = new Timestamp(sdf.parse(dateTo).getTime());
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			try {
				result = projectService
						.getFilteredProjectsWithCountAndPagesCount(
								new Filter(dateF, dateT, statusId, null, 
										page, numberOfResults, false, false, false));
				List<ProjectDto> projectDtos = (List<ProjectDto>) result[0];
				Integer projectsCount = (Integer) result[1];
				Integer numOfPages = (Integer) result[2];
				
				request.setAttribute("projectDtos", projectDtos);
				request.setAttribute("projectsCount", projectsCount);
				request.setAttribute("numOfPages", numOfPages);
			} catch (SQLException | ParseException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (CurrentConnection.getCurrent() != null) {
					CurrentConnection.getCurrent().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher("/views/projects/allProjects.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String[] selectedProjectIds = request.getParameterValues("boxes");
			if (selectedProjectIds != null) {
				switch (request.getParameter("action")) {
					case "Change to New":
						for (int i = 0; i < selectedProjectIds.length; i++) {
							try {
								projectService.changeProjectStatus
										(Integer.parseInt(selectedProjectIds[i]), 1);
							} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
						break;
					case "Change to In progress":
						for (int i = 0; i < selectedProjectIds.length; i++) {
							try {
								projectService.changeProjectStatus
										(Integer.parseInt(selectedProjectIds[i]), 2);
							} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
						break;
					case "Change to Finished":
						for (int i = 0; i < selectedProjectIds.length; i++) {
							try {
								projectService.changeProjectStatus
										(Integer.parseInt(selectedProjectIds[i]), 3);
							} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
						break;
					case "Delete":
						for (int i = 0; i < selectedProjectIds.length; i++) {
							try {
								projectService.deleteProjectById
										(Integer.parseInt(selectedProjectIds[i]));
							} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
						break;
				}
			}
		} finally {
			try {
				if (CurrentConnection.getCurrent() != null) {
					CurrentConnection.getCurrent().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/projects/all");
	}
}
