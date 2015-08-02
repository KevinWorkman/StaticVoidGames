package com.StaticVoidGames.spring.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.comments.CommentView;
import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.spring.controller.interfaces.MemberControllerInterface;
import com.StaticVoidGames.spring.dao.CommentDao;
import com.StaticVoidGames.spring.dao.GameDao;
import com.StaticVoidGames.spring.dao.MemberDao;
import com.StaticVoidGames.spring.util.ActivationEmailSender;
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

		//TODO delete old profile picture?

		if(!profilePicture.isEmpty()){
			String awsAccessKey = env.getProperty("aws.accessKey");
			String awsSecretKey = env.getProperty("aws.secretKey");

			String bucket = env.getProperty("s3.bucket");
			String key = "users/" + memberName + "/" + profilePicture.getOriginalFilename();

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(profilePicture.getSize());

			try{

				AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
				s3.putObject(bucket, key, profilePicture.getInputStream(), meta);
				s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);
				member.setImageUrl(profilePicture.getOriginalFilename());
			}
			catch(Exception e){
				//TODO: let user know something went wrong
				e.printStackTrace();
			}
		}

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

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/members/activation.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/members/Member.java")
		}
				);

		member.setActivated(true);
		memberDao.updateMember(member);

		model.addAttribute("activationSuccess", true);
		return "members/activation";
	}

	@Override
	public String resetPassword(HttpServletRequest request, ModelMap model, String memberName, String activationId, HttpSession session) {


		Member member = memberDao.getMember(memberName);

		if(member == null){
			model.addAttribute("resetSuccess", false);
			model.addAttribute("resetMessage", "Invalid username.");

			return "members/resetPassword";
		}

		if(!member.isActivated()){
			model.addAttribute("resetSuccess", false);
			model.addAttribute("resetMessage", "Please activate your account (by clicking the link in the activation email) before resetting your password.");
			return "members/resetPassword";
		}

		if(!activationId.equals(member.getActivationId())){
			model.addAttribute("resetSuccess", false);
			model.addAttribute("resetMessage", "That reset code is not valid. Make sure you copy it correctly from the password reset email!");
			return "members/resetPassword";
		}

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/members/activation.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/members/Member.java")
		}
				);


		String password = ActivationEmailSender.generateRandomKey(12);
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(10);
		String bcryptHash = bcrypt.encode(password);
		member.setBcryptHash(bcryptHash);
		member.setActivationId(ActivationEmailSender.generateRandomKey(32));
		memberDao.updateMember(member);

		model.addAttribute("password", password);
		model.addAttribute("resetSuccess", true);

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/members/resetPassword.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/MemberController.java")
		}
				);

		return "members/resetPassword";

	}


	@Transactional
	@RequestMapping(value = "/{member}/changePassword", method = RequestMethod.GET)
	public String changePassword(HttpServletRequest request, ModelMap model, @PathVariable(value="member") String member, HttpSession session){

		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);

		if(!member.equals(loggedInMember)){
			return "login";
		}

		Member m = memberDao.getMember(member);

		if(m == null){
			return "redirect:/members/";
		}

		model.addAttribute("memberName", m.getMemberName());

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/members/changePassword.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/MemberController.java")
		}
				);

		return "members/changePassword";
	}

	@Transactional
	@RequestMapping(value = "/{member}/changePassword", method = RequestMethod.POST)
	public String changePasswordPost(HttpServletRequest request, ModelMap model, @PathVariable(value="member") String member, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword1") String newPassword1, @RequestParam("newPassword2") String newPassword2, HttpSession session){
		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);

		if(!member.equals(loggedInMember)){
			return "login";
		}

		Member m = memberDao.getMember(member);

		if(m == null){
			return "redirect:/members/";
		}

		model.addAttribute("memberName", m.getMemberName());

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(10);

		if(!bcrypt.matches(oldPassword, m.getBcryptHash())){
			model.addAttribute("error", "That doesn't seem to be your old password.");
			return "members/changePassword";
		}

		if(!newPassword1.equals(newPassword2)){
			model.addAttribute("error", "Please make sure to enter your new password twice.");
			return "members/changePassword";
		}

		m.setBcryptHash(bcrypt.encode(newPassword1));
		memberDao.updateMember(m);

		model.addAttribute("success", true);

		return "members/changePassword";
	}

	private int getForumPoints(String member){

		int likesGiven = 0;
		int likesReceived = 0;
		int topics = 0;
		int replies = 0;

		try{
			InputStream in = new URL("http://forum.StaticVoidGames.com/users/" + member + ".json").openStream();
			String jsonText = IOUtils.toString(in);
			IOUtils.closeQuietly(in);

			JSONObject obj = new JSONObject(jsonText);
			
			JSONArray arr = obj.getJSONObject("user").getJSONArray("stats");
			for (int i = 0; i < arr.length(); i++)
			{
				int type = arr.getJSONObject(i).getInt("action_type");
				int count =  arr.getJSONObject(i).getInt("count");

				if(type == 1){
					likesGiven = count;
				}
				else if(type == 2){
					likesReceived = count;
				}
				else if(type == 4){
					topics = count;
				}
				else if(type == 5){
					replies = count;
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		int totalPoints = likesGiven*1 + likesReceived*2 + replies*5 + topics*10;
		return totalPoints;
	}
	
	private void addForumDataToStats(String member, JSONObject stats){

		int likesGiven = 0;
		int likesReceived = 0;
		int topics = 0;
		int replies = 0;

		try{
			InputStream in = new URL("http://forum.StaticVoidGames.com/users/" + member + ".json").openStream();
			String jsonText = IOUtils.toString(in);
			IOUtils.closeQuietly(in);

			JSONObject obj = new JSONObject(jsonText);
			
			JSONArray arr = obj.getJSONObject("user").getJSONArray("stats");
			for (int i = 0; i < arr.length(); i++)
			{
				int type = arr.getJSONObject(i).getInt("action_type");
				int count =  arr.getJSONObject(i).getInt("count");

				if(type == 1){
					likesGiven = count;
				}
				else if(type == 2){
					likesReceived = count;
				}
				else if(type == 4){
					topics = count;
				}
				else if(type == 5){
					replies = count;
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		stats.append("likesGiven", likesGiven);
		stats.append("likesReceived", likesReceived);
		stats.append("replies", replies);
		stats.append("topics", topics);
	}
	
	private void addForumDataToToDo(String member, JSONArray todo){

		int likesGiven = 0;
		int likesReceived = 0;
		int topics = 0;
		int replies = 0;

		try{
			InputStream in = new URL("http://forum.StaticVoidGames.com/users/" + member + ".json").openStream();
			String jsonText = IOUtils.toString(in);
			IOUtils.closeQuietly(in);

			JSONObject obj = new JSONObject(jsonText);
			
			JSONArray arr = obj.getJSONObject("user").getJSONArray("stats");
			for (int i = 0; i < arr.length(); i++)
			{
				int type = arr.getJSONObject(i).getInt("action_type");
				int count =  arr.getJSONObject(i).getInt("count");

				if(type == 1){
					likesGiven = count;
				}
				else if(type == 2){
					likesReceived = count;
				}
				else if(type == 4){
					topics = count;
				}
				else if(type == 5){
					replies = count;
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		if(likesGiven == 0){
			todo.put("Like a post on the forum!");
		}
		if(replies == 0){
			todo.put("Post a reply on the forum!");
		}
		if(topics == 0){
			todo.put("Post a new topic on the forum!");
		}
	}

	@Transactional
	public @ResponseBody String memberPoints(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member){

		try{
			
			int sitePoints = memberDao.getPoints(member);
			int forumPoints = getForumPoints(member);
			
			String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);
			
			if(member.equals(loggedInMember)){
				session.setAttribute("points", sitePoints+forumPoints);
			}

			
			return String.valueOf(sitePoints + forumPoints);
		}
		catch(Exception e){
			e.printStackTrace();
			return "0";
		}
	}
	
	@Transactional
	@RequestMapping(value = "/{member}/stats", method = RequestMethod.GET)
	public @ResponseBody String memberStats(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member){
		JSONObject obj = new JSONObject();
		obj.append("commentCount", memberDao.getCommentCount(member));
		obj.append("gameCount", memberDao.getGameCount(member));
		obj.append("blogCount", memberDao.getBlogCount(member));
		addForumDataToStats(member, obj);
		
		return obj.toString();
	}
	
	@Transactional
	@RequestMapping(value = "/{member}/todo", method = RequestMethod.GET)
	public @ResponseBody String memberToDo(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member){
		
		Member memberObj = memberDao.getMember(member);
		
		int gameCommentCount = memberDao.getGameCommentCount(member);
		int blogCommentCount = memberDao.getBlogCommentCount(member);
		int accountCommentCount = memberDao.getAccountCommentCount(member);
		int gameCount = memberDao.getGameCount(member);
		int blogCount = memberDao.getBlogCount(member);
		String description = memberObj.getDescription();
		String image = memberObj.getProfileImageUrl();
		
		JSONArray array = new JSONArray();
		
		if(image == null){
			array.put("Upload a profile picture!");
		}
		if(description == null){
			array.put("Enter a description of yourself on your profile page!");
		}
		if(gameCommentCount == 0){
			array.put("Play a game and leave a comment!");
		}
		if(blogCommentCount == 0){
			array.put("Leave a comment on a blog entry!");
		}
		if(accountCommentCount == 0){
			array.put("Leave a comment on somebody's profile page!");
		}
		if(gameCount == 0){
			array.put("Publish a game!");
		}
		if(blogCount == 0){
			array.put("Write a blog!");
		}

		
		addForumDataToToDo(member, array);
		
		if(array.length() == 0){
			return null;
		}
		return array.toString();
		
	}
	
	
}
