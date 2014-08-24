package com.StaticVoidGames.spring.controller.interfaces;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public interface CommentControllerInterface {
	
	@Transactional
	@RequestMapping(value="/delete/comment/{id}", method=RequestMethod.GET)
	public String deleteComment(@PathVariable(value="id") String id,  HttpSession session);
	
	@Transactional
	@RequestMapping(value="/add/{type}/{thingCommentedOn}", method=RequestMethod.POST)
	public String addComment(@PathVariable(value="type") String type, @PathVariable(value="thingCommentedOn") String thingCommentedOn, @RequestParam("commentInput") String commentInput, HttpSession session);
	
}
