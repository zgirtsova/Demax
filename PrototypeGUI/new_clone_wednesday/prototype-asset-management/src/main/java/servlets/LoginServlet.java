package servlets;

import dtos.users.UserDTO;
import services.UserService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("todo", "10");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            userService = new UserService();
            UserDTO user = this.userService.getByEmailAndPassword(request.getParameter("email"), request.getParameter("password"));
            String userName = user.getFirstName() + " " + user.getLastName();
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userNames", userName);
            response.sendRedirect("/index");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}