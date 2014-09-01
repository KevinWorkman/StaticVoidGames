package com.StaticVoidGames.spring.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/irc")
public interface IRCControllerInterface {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewIRCIndex(ModelMap map);
}
