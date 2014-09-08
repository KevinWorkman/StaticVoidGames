package com.StaticVoidGames.spring.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/JarMatey")
public interface JarMateyControllerInterface {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewIndex(ModelMap map);

	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String viewPage(ModelMap map, @PathVariable(value="page") String page);
}
