package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dtos.dashboards.DashboardDTO;
import services.DashboardService;

import java.io.IOException;

@WebServlet("/index")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DashboardService dashboardService = new DashboardService();

        try {
            // userId should come from localStorage !!
            int userId = 1;

            DashboardDTO dashboardDTO = dashboardService.getDashboardDTO(userId);
            req.setAttribute("dashboardDTO", dashboardDTO);

            resp.setContentType("text/html");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
