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
		//random
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-2WjDL3s/0/O/lights3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-3NPvKtv/0/O/lights2.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-xc7q5Xx/0/O/lights1.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-gGBQK2k/1/O/lights4.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2013/i-fHZpVNs/0/O/lights9.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2011/i-gnNp3t9/1/O/Night1.jpg");


		//Halloween
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2008/i-pF3wBX6/1/X3/RedLeaves-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2008/i-NqzMgHD/1/X3/YellowLeaves-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2008/i-L2VfVv8/1/X3/GeeseTrees-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2009/i-8js2VN8/0/X3/TallFallTree-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2011/i-mHckHFS/0/X3/Leaves4-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2011/i-t26rcmj/0/X3/TreePanorama2-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2011/i-RHzkX4G/0/X3/Leaves2-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2011/i-83W6Gh2/0/X3/TreePanorama5-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2011/i-79ztj5k/0/X3/Leaves1-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2011/i-bNVwcxr/0/X3/Leaves96-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-3x8Gvrw/1/X3/Stanley1-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-gL6CZ9D/1/X3/insidePumpkin1-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-PTq36v8/1/X3/tree1-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-m4j7wpN/1/X3/tree2-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-MTd7J9S/1/X3/tree3-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-PWS9s2p/2/X3/sky1-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-SfxrsPB/1/X3/path1-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-bSgG2W2/1/X3/path5-X3.jpg");
		//		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-N5Nd76k/1/X3/path7-X3.jpg");

		//xmas
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2008/i-PR2nNVD/1/X3/Ball5-X3.jpg");
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2008/i-M3TXbKJ/1/X3/Lights2-X3.jpg");
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2009/i-sVVrNNt/0/X3/Wrapping-X3.jpg");
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2009/i-Lfcp5Q3/0/X3/Snowman-X3.jpg");
		backgroundImageUrls.add("http://photos.kevinworkman.com/Pictures/2012/i-Rd9KNzN/1/X3/LappTree1-X3.jpg");


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

		if(request.getRequestURL().toString().contains("/activate/")){
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
