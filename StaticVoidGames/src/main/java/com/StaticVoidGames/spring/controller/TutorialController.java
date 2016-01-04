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
import com.StaticVoidGames.spring.util.OpenSourceLink;
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
	public String viewTutorial(ModelMap model, @PathVariable(value="category") String category, @PathVariable(value="tutorial") String tutorial){

		//this is for old tutorial links
		//TODO: might be a way to handle this in the Tucky url rewriter?
		if(Character.isUpperCase(tutorial.charAt(0))){
			tutorial = StringUtils.uncapitalize(tutorial);
			return "redirect:/tutorials/" + category + "/" + tutorial;
		}
		
		if("index".equals(tutorial)){
			String title = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(category)), ' ');
			model.addAttribute("title", title + " - Static Void Games");
		}
		else{
			String title = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(tutorial)), ' ');
			model.addAttribute("title", title + " - Static Void Games");
		}
		
		
		model.addAttribute("chapter", category);
		model.addAttribute("section", tutorial);

		try{
			File file = new File( servletContext.getRealPath("/WEB-INF/tutorialsContent/" + category + "/" + tutorial + ".markdown") );
			String markdown = FileUtils.readFileToString(file);
			model.addAttribute("tutorialMarkdown", markdown);
		}
		catch(Exception e){
			e.printStackTrace();
			model.addAttribute("tutorialText", "That tutorial doesn't exist.");
		}
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/tutorials/viewTutorial.jsp"),
				new OpenSourceLink("View this page's content.", "https://github.com/KevinWorkman/StaticVoidGames/tree/master/StaticVoidGames/src/main/webapp/WEB-INF/tutorialsContent/"  + category + "/" + tutorial  + ".markdown"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/TutorialController.java")
			}
		);

		return "tutorials/viewTutorial";
	}

	/**
	 * TODO: Make the index page another markdown file and just call the other function to load it
	 */
	@Override
	public String tutorials(ModelMap model) {
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/tutorials/index.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/TutorialController.java")
			}
		);
		
		return "/tutorials/index";
	}
}
