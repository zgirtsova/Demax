package servlets;

import dtos.projects.NewProjectDTO;
import services.ProjectService;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/new-project")
public class NewProjectServlet extends HttpServlet {

    private ProjectService projectService = new ProjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        req.getRequestDispatcher("new-project.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String projectName = req.getParameter("project-name");
        String companyName = req.getParameter("company-name");
        String projectManager = req.getParameter("project-manager");
        int statusId = 1;

        NewProjectDTO newProjectDTO = new NewProjectDTO();
        newProjectDTO.setProjectName(projectName);
        newProjectDTO.setCompanyName(companyName);
        newProjectDTO.setProjectManager(projectManager);
        newProjectDTO.setStatusId(statusId);
        newProjectDTO.setDateCreated(LocalDateTime.now());

        projectService.addNewProject(newProjectDTO);

        resp.sendRedirect("projects?page=1&results=5");
    }

}