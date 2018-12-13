package servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginURI = req.getContextPath() + "/login";
        String resetPasswordURI = req.getContextPath() + "/reset-password";
        String testURI = req.getContextPath()+"/test";

        boolean loggedIn = session != null && session
        		.getAttribute("user") != null;
        boolean loginPage = req.getRequestURI().equals(loginURI);
        boolean resetPasswordPage = req.getRequestURI()
        		.equals(resetPasswordURI);
        boolean testPage = req.getRequestURI().equals(testURI);

        if (loggedIn || loginPage || resetPasswordPage || testPage) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(loginURI);
        }
	}

    @Override
    public void init(FilterConfig filterConfig) 
    		throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
