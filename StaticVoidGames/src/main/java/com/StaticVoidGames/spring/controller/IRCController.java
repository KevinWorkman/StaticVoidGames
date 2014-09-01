package com.StaticVoidGames.spring.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import com.StaticVoidGames.spring.controller.interfaces.IRCControllerInterface;

/**
 * Controller handling the irc pages.
 * The content of the about pages are markdown files that are parsed when an about page is visited.
 * Probably a silly way to do this, but it'll make it easier for people to modify them.
 */
@Component
public class IRCController implements IRCControllerInterface{

	@Autowired
	ServletContext servletContext;
	
	@Override
	public String viewIRCIndex(ModelMap map) {
		return viewIRCPage(map, "index");
	}

	@Override
	public String viewIRCPage(ModelMap map,  @PathVariable(value="page") String page) {
		map.addAttribute("page", page);

		return "irc/" + page;
	}
}
