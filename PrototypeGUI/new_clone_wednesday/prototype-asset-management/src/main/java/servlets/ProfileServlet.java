package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dtos.users.UserDTO;
import entities.User;
import services.UserService;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            UserDTO user = (UserDTO)req.getSession().getAttribute("user");

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
        //super.doPost(req, resp);

        String oldPassword = req.getParameter("oldPass");
        String newPassword = req.getParameter("newPass");
        String newPassword2 = req.getParameter("newPassConfirm");

        try {
            if(newPassword.equals(newPassword2)	&& newPassword.length() <= 8) {
                UserDTO userDTO = (UserDTO)req.getSession().getAttribute("user");
                userService.updatePassword(userDTO, oldPassword, newPassword);
                resp.sendRedirect("/index");
                //req.getRequestDispatcher("profile.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}