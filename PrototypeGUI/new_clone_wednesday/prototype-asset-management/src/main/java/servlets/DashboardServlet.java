package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dtos.dashboards.DashboardDTO;
import dtos.users.UserDTO;
import services.DashboardService;

import java.io.IOException;

@WebServlet("/index")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DashboardService dashboardService = new DashboardService();

        try {
            UserDTO user = (UserDTO) req.getSession().getAttribute("user");
            int userId = user.getId();

            DashboardDTO dashboardDTO = dashboardService.getDashboardDTO(userId);
            req.setAttribute("dashboardDTO", dashboardDTO);

            resp.setContentType("text/html");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
