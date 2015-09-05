package com.StaticVoidGames.spring.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class will catch any Exceptions thrown by a controller and redirect.
 * TODO: This doesn't actually do much. Set up some error pages.
 */
@ControllerAdvice
public class ErrorConfig{
	
	/**
	 * Catch the ClientAbortException that can occur when a user requests a new page before the old request has completed.
	 * (At least, I think that's what causes this error.)
	 * If that exception hits the regular exception handler and goes to a JSP view, then we get another error complaining
	 * about writing to a closed writer. This doesn't hurt anything (I think), and gets in the way of debugging, so
	 * let's just ignore it.
	 * <br/>TODO: ClientAbortException is apparently on Tomcat's classpath, not our webapp's, so we're catching IOException?
	 * This is bad if we have other IOExceptions! 
	 * http://stackoverflow.com/questions/8271843/how-to-exclude-clientabortexception-from-simplemappingexceptionresolver
	 */
	@ExceptionHandler(IOException.class)
	public String handleClientAbortException (HttpServletRequest request, HttpServletResponse response, HttpSession session, Exception e) {
		Logger.getRootLogger().error("Caught IOException " + e.getMessage());
		Logger.getRootLogger().error("Request URI: " + request.getRequestURI());
		return null;
	}

	@ExceptionHandler(Exception.class)
	public String handleException (HttpServletRequest request, HttpServletResponse response, HttpSession session, Exception e) {
		Logger.getRootLogger().error("Query String: " + request.getQueryString());
		Logger.getRootLogger().error("Content Length: " + request.getContentLength());
		Logger.getRootLogger().error("Content Type: " + request.getContentType());
		Logger.getRootLogger().error("Request URI: " + request.getRequestURI());
		Logger.getRootLogger().error("Path Info: " + request.getPathInfo());
		Logger.getRootLogger().error("Server Name: " + request.getServerName());
		Logger.getRootLogger().error("Response Status: " + response.getStatus());
		Logger.getRootLogger().error("ErrorConfig.handleException", e);
		return "error";
	}
}
