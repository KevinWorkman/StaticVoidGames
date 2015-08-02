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
import org.springframework.web.context.request.async.DeferredResult;
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
	public String editMemberSubmit(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member, @RequestParam("profilePicture") MultipartFile profilePicture, @RequestParam("email") String email, @RequestParam("tag") String tag,  @RequestParam("description") String description,  @RequestParam(required=false, value="includeInLocalDatabase") Boolean includeInLocalDatabase);
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/{member}/points", method = RequestMethod.GET)
	public  String memberPoints(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member);
		
	@Transactional
	@RequestMapping(value = "/{member}/stats", method = RequestMethod.GET)
	public @ResponseBody String memberStats(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member);
	
	@Transactional
	@RequestMapping(value = "/{member}/todo", method = RequestMethod.GET)
	public @ResponseBody String memberToDo(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member);
	
	@Transactional
	@RequestMapping(value = "/{member}/edit", method = RequestMethod.GET)
	public String editMember(HttpServletRequest request, ModelMap model, @PathVariable(value="member") String member, HttpSession session);
	
	@Transactional
	@RequestMapping(value = "/{member}/activate/{activationId}", method = RequestMethod.GET)
	public String activateMember(HttpServletRequest request, ModelMap model, @PathVariable(value="member") String member, @PathVariable(value="activationId") String activationId, HttpSession session);
	
	@Transactional
	@RequestMapping(value = "/{member}/resetPassword/{activationId}", method = RequestMethod.GET)
	public String resetPassword(HttpServletRequest request, ModelMap model, @PathVariable(value="member") String member, @PathVariable(value="activationId") String activationId, HttpSession session);
	
	@Transactional
	@RequestMapping(value = "/{member}/changePassword", method = RequestMethod.GET)
	public String changePassword(HttpServletRequest request, ModelMap model, @PathVariable(value="member") String member, HttpSession session);
	
	@Transactional
	@RequestMapping(value = "/{member}/changePassword", method = RequestMethod.POST)
	public String changePasswordPost(HttpServletRequest request, ModelMap model, @PathVariable(value="member") String member, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword1") String newPassword1, @RequestParam("newPassword2") String newPassword2, HttpSession session);
	
	
}
