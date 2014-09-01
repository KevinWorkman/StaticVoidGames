package com.StaticVoidGames.spring.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.StaticVoidGames.spring.controller.interfaces.IRCControllerInterface;

/**
 * Controller handling the irc page.
 */
@Component
public class IRCController implements IRCControllerInterface{
	
	@Override
	public String viewIRCIndex(ModelMap map) {
		return "irc/index";
	}
}
