package com.StaticVoidGames.spring.controller.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * This controller returns json objects representing various facts about the site.
 * Mostly this is so we can use ajax to populate views.
 * TODO: probably easier ways to create json
 */
@Controller
@RequestMapping("/api")
public interface ApiControllerInterface {
	

	@Transactional
	@ResponseBody
	@RequestMapping(value = "/members/{member}/points", method = RequestMethod.GET)
	public  String memberPoints(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member);
		
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/members/{member}/stats", method = RequestMethod.GET)
	public String memberStats(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member);
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/members/{member}/todo", method = RequestMethod.GET)
	public  String memberToDo(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member);
	
	@Transactional
	@ResponseBody
	@RequestMapping(value="/myNotificationCount", method=RequestMethod.GET)
	public String getMyNotificationCount(HttpSession session);
	
	@ResponseBody
	@RequestMapping(value = "wait/myNotificationCount", method = RequestMethod.GET)
	public DeferredResult<String> waitForMyNotificationCount(HttpSession session);

	
}
