package com.StaticVoidGames.spring.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import com.StaticVoidGames.spring.controller.interfaces.TutorialControllerInterface;
import com.StaticVoidGames.spring.util.PageDownUtils;

/**
 * Controller responsible for tutorial pages.
 * Tutorial content is saved as markdown files. This controller parses them to create HTML to show to the user for each tutorial page.
 *
 */
@Component
public class TutorialController implements TutorialControllerInterface {

	@Autowired
	ServletContext servletContext;

	@Override
	public String viewTutorial(ModelMap map, @PathVariable(value="category") String category, @PathVariable(value="tutorial") String tutorial){

		//this is for old tutorial links
		//TODO: might be a way to handle this in the Tucky url rewriter?
		if(Character.isUpperCase(tutorial.charAt(0))){
			tutorial = StringUtils.uncapitalize(tutorial);
			return "redirect:/tutorials/" + category + "/" + tutorial;
		}
		
		map.addAttribute("chapter", category);
		map.addAttribute("section", tutorial);

		try{
			File file = new File( servletContext.getRealPath("/WEB-INF/tutorialsContent/" + category + "/" + tutorial + ".markdown") );
			String markdown = FileUtils.readFileToString(file);
			String html = PageDownUtils.getSanitizedHtml(markdown);
			map.addAttribute("tutorialText", html);
		}
		catch(Exception e){
			e.printStackTrace();
			map.addAttribute("tutorialText", "That tutorial doesn't exist.");
		}

		return "tutorials/viewTutorial";
	}

	/**
	 * TODO: Make the index page another markdown file and just call the other function to load it
	 */
	@Override
	public String tutorials(ModelMap map) {
		return "/tutorials/index";
	}
}
