package com.StaticVoidGames.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.StaticVoidGames.spring.dao.GameDao;
import com.StaticVoidGames.spring.dao.NotificationsDao;
import com.StaticVoidGames.spring.util.AttributeNames;

/**
 * The Interceptor methods are called before and after loading every page. This lets us inject stuff into every page and keep track of pages visited.
 *
 */
@Component
public class Interceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private NotificationsDao notificationsDao;
	
	@Autowired
	private GameDao gameDao;
	
	private List<String> backgroundImageUrls = new ArrayList<String>();
	
	public Interceptor(){
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-2WjDL3s/0/O/lights3.jpg");
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-3NPvKtv/0/O/lights2.jpg");
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-xc7q5Xx/0/O/lights1.jpg");
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-gGBQK2k/1/O/lights4.jpg");
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-fHZpVNs/0/O/lights9.jpg");
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2011/i-gnNp3t9/1/O/Night1.jpg");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		
		request.setAttribute("backgroundImage", backgroundImageUrls.get((int) (Math.random()* backgroundImageUrls.size())));
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
		
		if(request.getRequestURL().toString().endsWith("/notifications")){
			return;
		}
		if(request.getRequestURL().toString().endsWith("/login")){
			return;
		}
		if(request.getRequestURL().toString().endsWith("/register")){
			return;
		}
		if(request.getRequestURL().toString().endsWith("/ajaxNotificationCount")){
			return;
		}
		if(request.getRequestURL().toString().endsWith("/logout")){
			return;
		}
		if(request.getRequestURL().toString().endsWith("/oldLogin")){
			return;
		}
		
		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);

		//TODO this screws up if you have multiple tabs open
		if(request.getQueryString() != null){
			request.getSession().setAttribute(AttributeNames.lastPageVisited, request.getRequestURL().toString() + "?" + request.getQueryString());
		}
		else{
			request.getSession().setAttribute(AttributeNames.lastPageVisited, request.getRequestURL().toString());
		}
		
		System.out.println(loggedInMember + " visited " +  request.getRequestURL().toString());
		request.setAttribute(AttributeNames.s3Endpoint, env.getProperty("s3.endpoint"));
	}
}
