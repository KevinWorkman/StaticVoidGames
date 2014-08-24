package com.StaticVoidGames.spring.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/about")
public interface AboutControllerInterface {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewAboutIndex(ModelMap map);

	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String viewAboutPage(ModelMap map, @PathVariable(value="page") String page);
}
