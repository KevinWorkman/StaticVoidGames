package com.StaticVoidGames.spring.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tutorials")
public interface TutorialControllerInterface {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String tutorials(ModelMap map);

	@RequestMapping(value = "/{category}/{tutorial}", method = RequestMethod.GET)
	public String viewTutorial(ModelMap map, @PathVariable(value="category") String category, @PathVariable(value="tutorial") String tutorial);
}
