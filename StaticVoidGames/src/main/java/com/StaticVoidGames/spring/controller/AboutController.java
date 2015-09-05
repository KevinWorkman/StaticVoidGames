package com.StaticVoidGames.spring.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import com.StaticVoidGames.spring.controller.interfaces.AboutControllerInterface;
import com.StaticVoidGames.spring.util.OpenSourceLink;
import com.StaticVoidGames.spring.util.PageDownUtils;

/**
 * Controller handling the about pages.
 * The content of the about pages are markdown files that are parsed when an about page is visited.
 * Probably a silly way to do this, but it'll make it easier for people to modify them.
 */
@Component
public class AboutController implements AboutControllerInterface{

	@Autowired
	ServletContext servletContext;
	
	@Override
	public String viewAboutIndex(ModelMap map) {
		return viewAboutPage(map, "index");
	}

	@Override
	public String viewAboutPage(ModelMap map,  @PathVariable(value="page") String page) {
		
		long start = System.currentTimeMillis();
		map.addAttribute("page", page);
		
		map.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/about/viewAboutPage.jsp"),
				new OpenSourceLink("View this page's content.", "https://github.com/KevinWorkman/StaticVoidGames/tree/master/StaticVoidGames/src/main/webapp/WEB-INF/aboutContent/"  + page + ".markdown"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/AboutController.java")
				}
		);

		try{
			File file = new File( servletContext.getRealPath("/WEB-INF/aboutContent/" + page + ".markdown") );

			String markdown = FileUtils.readFileToString(file);
			String html = PageDownUtils.getSanitizedHtml(markdown);

			map.addAttribute("aboutText", html);
		}
		catch(Exception e){
			e.printStackTrace();
			map.addAttribute("aboutText", "Could not find that about page.");
		}
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Elapsed: " + elapsed);
		
		

		return "about/viewAboutPage";
	}
}
