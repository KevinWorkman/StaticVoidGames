package com.StaticVoidGames.spring.controller.interfaces;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public interface NotificationsControllerInterface {

	@Transactional
	@RequestMapping(value="/subscriptions", method = RequestMethod.GET)
    public String viewYourSubscriptions(ModelMap model, HttpSession session);
	
	@Transactional
	@RequestMapping(value="/notifications", method = RequestMethod.GET)
    public String viewYourNotifications(ModelMap model, HttpSession session);
	
	@Transactional
	@RequestMapping(value="/events", method = RequestMethod.GET)
    public String viewSiteEvents(ModelMap model, HttpSession session);

	@Transactional
	@RequestMapping(value="/ajaxNotificationCount", method=RequestMethod.GET)
	public @ResponseBody String getAjaxNotificationCount(HttpSession session);
	
	
	
}
