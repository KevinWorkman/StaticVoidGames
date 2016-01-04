package com.StaticVoidGames.spring.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.StaticVoidGames.spring.controller.interfaces.JarMateyControllerInterface;
import com.StaticVoidGames.spring.util.OpenSourceLink;
import com.StaticVoidGames.spring.util.PageDownUtils;

@Component
public class JarMateyController implements JarMateyControllerInterface{
	
	@Autowired
	ServletContext servletContext;

	@Override
	public String viewIndex(ModelMap map) {
		return viewPage(map, "index");
	}

	@Override
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String viewPage(ModelMap map, @PathVariable(value="page") String page) {
		map.addAttribute("page", page);
		
//		map.addAttribute("openSourceLinks", 
//				new OpenSourceLink[]{
//				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/about/viewAboutPage.jsp"),
//				new OpenSourceLink("View this page's content.", "https://github.com/KevinWorkman/StaticVoidGames/tree/master/StaticVoidGames/src/main/webapp/WEB-INF/aboutContent/"  + page + ".markdown"),
//				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/AboutController.java")
//				}
//		);

		try{
			File file = new File( servletContext.getRealPath("/WEB-INF/JarMateyContent/" + page + ".markdown") );

			String markdown = FileUtils.readFileToString(file);

			map.addAttribute("markdown", markdown);
		}
		catch(Exception e){
			e.printStackTrace();
			map.addAttribute("text", "Could not find that page.");
		}
		
		
		

		return "JarMatey/viewPage";
	}

}
