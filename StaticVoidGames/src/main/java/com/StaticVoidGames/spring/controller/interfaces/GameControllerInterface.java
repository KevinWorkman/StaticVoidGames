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

@Controller
@RequestMapping("/games")
public interface GameControllerInterface {
	
	@Transactional
	@RequestMapping("")
    public String listGames(HttpServletRequest request, ModelMap model, HttpSession session);

	
	@Transactional
	@RequestMapping(value = {"/{game}"}, method = RequestMethod.GET)
	public String viewGame(HttpServletRequest request, ModelMap model, @PathVariable(value="game") String game, HttpSession session);
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newGame(ModelMap model, HttpSession session);
	
	@Transactional
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String newGamePost(ModelMap model, HttpSession session, @RequestParam("gameUrlName") String gameUrlName);
	
	
}
