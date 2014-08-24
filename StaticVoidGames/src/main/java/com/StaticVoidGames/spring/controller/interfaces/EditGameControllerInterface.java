package com.StaticVoidGames.spring.controller.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.StaticVoidGames.games.GameForm;

@Controller
@RequestMapping("/games/{game}/edit")
public interface EditGameControllerInterface {

	@Transactional
	@RequestMapping(method=RequestMethod.GET)
	public String showIndex(@PathVariable("game") String game, HttpSession session, ModelMap model);
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public String post(@PathVariable(value="game") String game, @ModelAttribute("gameForm") GameForm gameForm, HttpSession session, HttpServletRequest request);
	
	@Transactional
	@RequestMapping(value="/{section}", method=RequestMethod.GET)
	public String editSection(@PathVariable(value="game") String game, @PathVariable(value="section") String section, ModelMap model, HttpSession session, HttpServletRequest request);
	
}
