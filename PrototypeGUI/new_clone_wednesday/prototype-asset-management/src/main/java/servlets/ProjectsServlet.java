package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dtos.projects.ProjectDTO;
import dtos.projects.ProjectsPerPageDTO;
import helpers.Filter;
import helpers.FilterNames;
import services.ProjectService;

@WebServlet("/projects/*")
public class ProjectsServlet extends BaseListView {
    private ProjectService projectService = new ProjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String> parameters = super.getParametersMap(req);
            List<Filter> filters = super.getFilters(parameters);

            int page = Integer.parseInt(parameters.get("page"));
            int resultPerPage = Integer.parseInt(parameters.get("results"));

            ProjectsPerPageDTO projectsPerPageDTO = projectService.getProjectsPerPage(resultPerPage, page, filters);
            List<ProjectDTO> projects = projectsPerPageDTO.getProjectsPerPage();
            int filteredProjectsCount = projectsPerPageDTO.getFilteredProjectsCount();

            req.setAttribute("projects", projects);
            req.setAttribute("filteredProjectsCount", filteredProjectsCount);

            resp.setContentType("text/html");
            req.getRequestDispatcher("projects.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageParam = req.getParameter("page");
        String projectParam = req.getParameter("results");
        String statusParam = req.getParameter("status");
        String dateFromParam = req.getParameter("date-from");

        String dateToParam = req.getParameter("date-to");

        String actionIndexParam = req.getParameter("action-index");
        String[] projectsIdsParam = req.getParameterValues("project-id");

        if (actionIndexParam != null && projectsIdsParam != null) {
            int actionIndex = Integer.parseInt(actionIndexParam);
            int[] projectsIds = Arrays.stream(projectsIdsParam).mapToInt(Integer::parseInt).toArray();

            this.applyActions(actionIndex, projectsIds);
        }

        String url = parseURL("projects", pageParam, projectParam, statusParam, dateFromParam, dateToParam);
        resp.sendRedirect(url);
    }

    private void applyActions(int actionIndex, int[] projectsIds) {
        switch (actionIndex) {
            case 1:
            case 2:
            case 3:
                projectService.updateProjectsStatusesByIds(projectsIds, actionIndex);
                break;
            case 4:
                projectService.deleteProjectsByIds(projectsIds);
                break;
        }
    }
}