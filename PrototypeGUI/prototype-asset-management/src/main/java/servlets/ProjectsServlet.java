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
import helpers.Filter;
import helpers.FilterNames;
import services.ProjectService;

@WebServlet("/projects/*")
public class ProjectsServlet extends HttpServlet {
    private ProjectService projectService = new ProjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String> parameters = this.getParametersMap(req);
            List<Filter> filters = this.getFilters(parameters);

            String pageParam = parameters.get("page");
            String projectParam = parameters.get("results");
            int page = Integer.parseInt(pageParam);
            int projectsCount = Integer.parseInt(projectParam);

            List<ProjectDTO> projects = projectService.getProjectsPerPage(projectsCount, page, filters);

            if (projects == null) {
                throw new Exception();
            }

            req.setAttribute("projects", projects);

            resp.setContentType("text/html");
            req.getRequestDispatcher("projects.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("<h1>No projects found</h1>");
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

        String url = parseURL(pageParam, projectParam, statusParam, dateFromParam, dateToParam);
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

    private String parseURL(String pageParam, String projectParam, String statusParam, String dateFromParam, String dateToParam) {
        String url = String.format("projects?page=%s&results=%s", pageParam, projectParam);

        if (statusParam != null && !statusParam.isEmpty() && !statusParam.equals("0")) {
            url += "&status=" + statusParam;
        }

        if (dateFromParam != null && !dateFromParam.isEmpty()) {
            url += "&date-from=" + dateFromParam;
        }

        if (statusParam != null && !dateToParam.isEmpty()) {
            url += "&date-to=" + dateToParam;
        }

        return url;
    }

    private List<Filter> getFilters(Map<String, String> parameters) {
        List<Filter> filters = new ArrayList<>();

        String dateFromParam = parameters.get("date-from");
        String dateToParam = parameters.get("date-to");
        String status = parameters.get("status");

        if (dateFromParam != null) {
            int[] dateFromParams = Arrays.stream(dateFromParam.split("-")).mapToInt(Integer::parseInt).toArray();
            LocalDateTime dateFrom =
                    LocalDateTime.of(dateFromParams[0], dateFromParams[1], dateFromParams[2], 0, 0);

            Filter<LocalDateTime> filter = new Filter<>();
            filter.setName(FilterNames.DATE_FROM);
            filter.setValue(dateFrom);

            filters.add(filter);
        }

        if (dateToParam != null) {
            int[] dateToParams = Arrays.stream(dateToParam.split("-")).mapToInt(Integer::parseInt).toArray();
            LocalDateTime dateTo =
                    LocalDateTime.of(dateToParams[0], dateToParams[1], dateToParams[2], 0, 0);

            Filter<LocalDateTime> filter = new Filter<>();
            filter.setName(FilterNames.DATE_TO);
            filter.setValue(dateTo);

            filters.add(filter);
        }

        if (status != null) {
            Filter<Integer> filter = new Filter<>();
            try {
                int statusId = Integer.parseInt(status);
                filter.setName(FilterNames.STATUS_ID);
                filter.setValue(statusId);

                filters.add(filter);
            } catch (NumberFormatException e) {
            }
        }

        if (filters.size() == 0) {
            return null;
        }

        return filters;
    }

    private Map<String, String> getParametersMap(HttpServletRequest req) {
        Map<String, String> params = new HashMap<>();

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = req.getParameter(paramName);

            params.put(paramName, paramValue);
        }

        return params;
    }
}