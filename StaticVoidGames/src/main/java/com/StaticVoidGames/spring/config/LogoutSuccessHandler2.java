package com.StaticVoidGames.spring.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.StaticVoidGames.spring.util.AttributeNames;

/**
 * This class is used by Spring to do stuff after a user logs out.
 * It has a 2 in its name to not confuse it with org.springframework.security.web.authentication.logout.LogoutSuccessHandler
 */
public class LogoutSuccessHandler2 extends SimpleUrlLogoutSuccessHandler{

	/**
	 * Method called when the user logs out. Removes the login attributes from the session and redirects to the last page visited.
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		
		request.getSession().setAttribute(AttributeNames.isLoggedIn, false);
		request.getSession().removeAttribute(AttributeNames.loggedInUser);	
		
		
		String url = "/";
		Object urlObj = request.getSession().getAttribute("loginUrl");
		if(urlObj != null){
			url = (String) request.getSession().getAttribute(AttributeNames.lastPageVisited);
		}
		getRedirectStrategy().sendRedirect(request, response, url);
	}
}
