package com.sizenith.poc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sizenith.poc.context.BeanFactory;
import com.sizenith.poc.context.BeanFactory.BeanName;
import com.sizenith.poc.domain.User;
import com.sizenith.poc.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="Login", urlPatterns= {"/Login"}, loadOnStartup=1)
public class LoginServlet extends HttpServlet {
	
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
    	BeanFactory beanFactory = (BeanFactory) super.getServletContext().getAttribute("BeanFactory");
    	if (beanFactory == null) {
    		throw new ServletException("BeanFactory has not been initialized properly");
    	}
    	this.loginService = (LoginService) beanFactory.getBean(BeanName.LOGIN_SERVICE);
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
