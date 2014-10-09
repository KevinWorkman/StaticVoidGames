package com.StaticVoidGames.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.comments.CommentView;
import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.spring.controller.interfaces.MemberControllerInterface;
import com.StaticVoidGames.spring.dao.CommentDao;
import com.StaticVoidGames.spring.dao.GameDao;
import com.StaticVoidGames.spring.dao.MemberDao;
import com.StaticVoidGames.spring.util.AttributeNames;
import com.StaticVoidGames.spring.util.OpenSourceLink;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

/**
 * Controller that handles Member profile pages.
 */
@Component
public class MemberController implements MemberControllerInterface{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public String showMember(ModelMap model, @PathVariable(value="member") String member, HttpSession session){
		
		Member m = memberDao.getMember(member);

		if(m == null){
			return "redirect:/members/";
		}
		
		String s3Endpoint = env.getProperty("s3.endpoint");

		model.addAttribute("member", m);

		model.addAttribute("memberName", member);
		if(m.getImageUrl() != null){
			model.addAttribute("profilePicture", env.getProperty("s3.endpoint") + "/users/" + member + "/" + m.getImageUrl());
		}
		model.addAttribute("publishedGames", gameDao.getPublishedGamesOfMember(m.getMemberName()));
		model.addAttribute("unpublishedGames", gameDao.getUnpublishedGamesOfMember(m.getMemberName()));
		model.addAttribute("s3Endpoint", s3Endpoint);

		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);
		model.addAttribute("isOwner", member.equals(loggedInMember));
		
		List<CommentView> commentViews = new ArrayList<CommentView>();
		for(Comment c : commentDao.getComments(member, "AccountComment")){
			Member commentMember = memberDao.getMember(c.getCommentingMember());
			
			CommentView cv = new CommentView(c, commentMember, s3Endpoint);
			commentViews.add(cv);
		}
		
		model.addAttribute("commentViews", commentViews);
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/members/showMember.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/MemberController.java")
			}
		);
		
		return "members/showMember";
	}

	@Override
	@Transactional
	public String listMembers(HttpServletRequest request,ModelMap model, HttpSession session) {
		model.addAttribute("members", memberDao.getAllMembers());
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/members/listMembers.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/MemberController.java")
			}
		);
		
		return "members/listMembers";
	}

	@Override
	@Transactional
	public String editMember(HttpServletRequest request, ModelMap model, @PathVariable("member") String member, HttpSession session) {

		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);

		if(!member.equals(loggedInMember)){
			return "login";
		}

		Member m = memberDao.getMember(member);

		if(m == null){

			return "redirect:/members/";
		}

		model.addAttribute("member", m);
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/members/editMember.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/MemberController.java")
			}
		);

		return "members/editMember";
	}
	
	@Override
	public String editMemberSubmit(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String memberName, @RequestParam("profilePicture") MultipartFile profilePicture, @RequestParam("email") String email, @RequestParam("tag") String tag,  @RequestParam("description") String description,  @RequestParam(required=false, value="includeInLocalDatabase") Boolean includeInLocalDatabase){
	
		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);

		if(loggedInMember == null || !loggedInMember.equals(memberName)){
			return "redirect:/members/"+memberName;
		}
		
		Member member = memberDao.getMember(memberName);
		
		if(profilePicture != null){
		System.out.println("Image: " + profilePicture.getOriginalFilename());
		}
		else{
			System.out.println("IMAGE IS NULL");
		}

//		String awsAccessKey = env.getProperty("aws.accessKey");
//		String awsSecretKey = env.getProperty("aws.secretKey");
//		
//		String bucket = env.getProperty("s3.bucket");
//		String key = "users/" + memberName + "/" + profilePicture.getOriginalFilename();
//
//		ObjectMetadata meta = new ObjectMetadata();
//		meta.setContentLength(profilePicture.getSize());
//
//		try{
//			AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
//			s3.putObject(bucket, key, profilePicture.getInputStream(), meta);
//			s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);
//			member.setImageUrl(profilePicture.getOriginalFilename());
//		}
//		catch(Exception e){
//			//TODO: let user know something went wrong
//			e.printStackTrace();
//		}
		
		member.setEmail(email);
		member.setDescription(description);
		member.setIncludeInLocalDatabase(includeInLocalDatabase!= null? includeInLocalDatabase : false);
		member.setTag(tag);
		memberDao.updateMember(member);

		return "redirect:/members/"+memberName;
	}
	
	
	@Transactional
	@RequestMapping(value = "/{member}/activate/{activationId}", method = RequestMethod.GET)
	public String activateMember(HttpServletRequest request, ModelMap model, @PathVariable(value="member") String memberName, @PathVariable(value="activationId") String activationId, HttpSession session){
		
		Member member = memberDao.getMember(memberName);
		
		if(member == null){
			model.addAttribute("activationSuccess", false);
			model.addAttribute("activationMessage", "Invalid username.");
			
			return "members/activation";
		}
		
		if(member.isActivated()){
			model.addAttribute("activationSuccess", false);
			model.addAttribute("activationMessage", "Your account was already activated.");
			return "members/activation";
		}
		
		if(!activationId.equals(member.getActivationId())){
			model.addAttribute("activationSuccess", false);
			model.addAttribute("activationMessage", "That activation code is not valid. Make sure you copy it correctly from the activation email!");
			return "members/activation";
		}
		
		member.setActivated(true);
		memberDao.updateMember(member);
		
		model.addAttribute("activationSuccess", true);
		return "members/activation";
	}
	
	
}
