package com.StaticVoidGames.spring.controller.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blog")
public interface BlogControllerInterface {
	@Transactional
	@RequestMapping("")
    public String listBlogs(HttpServletRequest request, ModelMap model, HttpSession session);
	
	@Transactional
	@RequestMapping(value="/{blogUrlId}", method = RequestMethod.GET)
    public String viewBlogEntry(HttpServletRequest request, ModelMap model, @PathVariable(value="blogUrlId") String blogUrlId, HttpSession session);

	@Transactional
	@RequestMapping(value="/{blogUrlId}/edit", method = RequestMethod.GET)
    public String editBlogEntry(HttpServletRequest request, ModelMap model, @PathVariable(value="blogUrlId") String blogUrlId, HttpSession session);
	
	@Transactional
	@RequestMapping(value="/{blogUrlId}/thumbnail", method = RequestMethod.GET)
    public @ResponseBody String getBlogThumbnail(HttpServletRequest request, ModelMap model, @PathVariable(value="blogUrlId") String blogUrlId, HttpSession session);

	@Transactional
	@RequestMapping(value="/{blogUrlId}/edit", method = RequestMethod.POST)
    public String editBlogEntrySubmit(HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam("text") String text);

	@Transactional
	@RequestMapping(value="/new", method = RequestMethod.GET)
    public String newBlogEntry(ModelMap model,  HttpSession session);

	@Transactional
	@RequestMapping(value="/new", method = RequestMethod.POST)
    public String newBlogEntryPost(HttpSession session, @RequestParam("title") String title, @RequestParam("text") String text);
}
