package com.StaticVoidGames.spring.controller.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public interface StaticVoidGamesControllerInterface {

	@RequestMapping(method = RequestMethod.GET)
    public String viewHomePage(HttpServletRequest request, ModelMap model, HttpSession session);
	
	@RequestMapping(value = "/siteMap", method = RequestMethod.GET)
	public String siteMap(ModelMap model, HttpSession session);
	
	@RequestMapping(value = "/login")
	public String login(ModelMap model, HttpSession session);

	@RequestMapping(value = "/logout-success", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session);

	@RequestMapping(value = "/points", method = RequestMethod.GET)
	public String points(ModelMap model, HttpSession session) throws Exception;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(ModelMap model, HttpSession session);
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(HttpServletRequest request, ModelMap model, HttpSession session,  @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password);	

	@RequestMapping(value = "/oldLogin", method = RequestMethod.GET)
	public String oldLogin(ModelMap model, HttpSession session);
	
	@RequestMapping(value = "/oldLogin", method = RequestMethod.POST)
	public String oldLoginPost(ModelMap model, HttpSession session,  @RequestParam("username") String username,  @RequestParam("password") String password);
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model, HttpSession session);
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public String forgotPasswordPost(HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam("username") String username);	


}
