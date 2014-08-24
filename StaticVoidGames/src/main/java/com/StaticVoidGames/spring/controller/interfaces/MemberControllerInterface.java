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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/members")
public interface MemberControllerInterface {
	
	@Transactional
	@RequestMapping("")
    public String listMembers(HttpServletRequest request, ModelMap model, HttpSession session);

	@Transactional
	@RequestMapping(value = "/{member}", method = RequestMethod.GET)
	public String showMember(ModelMap model, @PathVariable(value="member") String member, HttpSession session);
	
	@Transactional
	@RequestMapping(value = "/{member}/edit", method = RequestMethod.POST)
	public String editMemberSubmit(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member, @RequestParam("profilePicture") MultipartFile profilePicture);
	
	@Transactional
	@RequestMapping(value = "/{member}/edit", method = RequestMethod.GET)
	public String editMember(HttpServletRequest request, ModelMap model, @PathVariable(value="member") String member, HttpSession session);
	
}
