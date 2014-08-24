package com.StaticVoidGames.spring.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.StaticVoidGames.spring.util.AttributeNames;

/**
 * Class used by Spring to keep track of the currently logged-in user.
 */
@Service
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	
	/**
	 * This function is called after the user successfully logs in.
	 * Adds an attribute to the session to keep track of the currently logged-in user.
	 * TODO: When the site is update, all sessions are lost, so everybody is logged out. Maybe do something smarter (maybe with cookies?) so this doesn't happen?
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
					throws ServletException, IOException {

		//sessions never expire
		request.getSession().setMaxInactiveInterval(-1);
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedInUser = user.getUsername();
		
		System.out.println("LOGIN SUCCESS HANDLER");
		System.out.println("Logged in user: " + loggedInUser);

		request.getSession().setAttribute(AttributeNames.isLoggedIn, true);
		request.getSession().setAttribute(AttributeNames.loggedInUser, loggedInUser);
		
		//redirect to the last page visited
		//TODO: this acts weird if the user opens multiple tabs or hits the back button before logging in
		String url = "/";
		Object urlObj = request.getSession().getAttribute("loginUrl");
		if(urlObj != null){
			url = (String) request.getSession().getAttribute(AttributeNames.lastPageVisited);
		}
		
		this.getRedirectStrategy().sendRedirect(request, response, url);
	}

}