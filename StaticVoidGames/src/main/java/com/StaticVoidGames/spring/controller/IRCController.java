package com.StaticVoidGames.spring.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.StaticVoidGames.spring.controller.interfaces.IRCControllerInterface;
import com.StaticVoidGames.spring.util.OpenSourceLink;

/**
 * Controller handling the irc page.
 */
@Component
public class IRCController implements IRCControllerInterface{
	
	@Override
	public String viewIRCIndex(ModelMap model) {
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/irc/index.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/IRCController.java")
			}
		);
		
		return "irc/index";
	}
}
