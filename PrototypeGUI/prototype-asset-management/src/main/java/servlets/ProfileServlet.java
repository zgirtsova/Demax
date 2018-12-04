package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dtos.users.UserDTO;
import services.UserService;

@WebServlet("/profile/*")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();

        try {
            String idParam = req.getParameter("id");
            int id = Integer.parseInt(idParam);

            UserDTO user = userService.getUserDTO(id);

            if (user == null) {
                throw new Exception();
            }

            String name = user.getFirstName() + " " + user.getLastName();
            String role = user.getRole();

            req.setAttribute("dto", user);
            req.setAttribute("name", name);
            req.setAttribute("role", role);

            resp.setContentType("text/html");
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("Invalid profile id");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}