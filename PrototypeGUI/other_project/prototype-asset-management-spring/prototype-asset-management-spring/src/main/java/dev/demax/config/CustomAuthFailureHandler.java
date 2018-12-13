package dev.demax.config;
import org.springframework.stereotype.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.core.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;


@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final String RESPONSE_BAD_CREDENTIALS = "bad-credentials";
	
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
					throws IOException, ServletException {
		super.onAuthenticationFailure(request, response, exception);
		response.sendError(401);
		//response.sendError(401,RESPONSE_BAD_CREDENTIALS);
		response.flushBuffer();
	}
}