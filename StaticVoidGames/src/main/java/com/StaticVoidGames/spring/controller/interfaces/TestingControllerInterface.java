package com.StaticVoidGames.spring.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/testing")
public interface TestingControllerInterface {
	
	@RequestMapping(value = "/pagedown", method = RequestMethod.GET)
	public String testPageDownEditor();
	
	@RequestMapping(value = "/pagedown", method = RequestMethod.POST)
	public String testPageDownSubmit(ModelMap map, @RequestParam("PageDownInput") String pageDownInput);
}
