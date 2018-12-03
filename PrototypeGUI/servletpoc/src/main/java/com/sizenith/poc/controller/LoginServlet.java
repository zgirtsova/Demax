package com.sizenith.poc.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.sizenith.poc.dao.LoginDAO;
import com.sizenith.poc.domain.User;
import com.sizenith.poc.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="Login", urlPatterns= {"/Login"})
public class LoginServlet extends HttpServlet {
	
	@Resource(name="jdbc/servlet_poc_ds")
	private DataSource dataSource;
	
	private LoginService loginService;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	loginService = new LoginService(new LoginDAO(dataSource));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login/login.jsp");
        dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = loginService.findUserByEmail(request.getParameter("email"), request.getParameter("password"));
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login/profile.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
