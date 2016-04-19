package com.StaticVoidGames.spring.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.spring.controller.interfaces.ApiControllerInterface;
import com.StaticVoidGames.spring.dao.MemberDao;
import com.StaticVoidGames.spring.dao.NotificationsDao;
import com.StaticVoidGames.spring.util.AttributeNames;

@Component
public class ApiController implements ApiControllerInterface{
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private NotificationsDao notificationsDao;
	

	
	public ApiController(){
		
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/members/{member}/points", method = RequestMethod.GET)
	public synchronized String memberPoints(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member){
			
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
	@ResponseBody
	@RequestMapping(value = "/members/{member}/stats", method = RequestMethod.GET)
	public String memberStats(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member){
		JSONObject obj = new JSONObject();
		obj.append("commentCount", memberDao.getCommentCount(member));
		obj.append("gameCount", memberDao.getGameCount(member));
		obj.append("blogCount", memberDao.getBlogCount(member));
		addForumDataToStats(member, obj);
		
		return obj.toString();
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/members/{member}/todo", method = RequestMethod.GET)
	public  String memberToDo(HttpServletRequest request, ModelMap model, HttpSession session, @PathVariable("member") String member){

		
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
	
	@Transactional
	@ResponseBody
	@RequestMapping(value="/myNotificationCount", method=RequestMethod.GET)
	public  String getMyNotificationCount(HttpSession session){


		try{
			String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);

			if(loggedInMember == null){
				return "0";
			}
			
			Member member = memberDao.getMember(loggedInMember);
			
			if(member == null){
				return "0";
			}
			
			return String.valueOf(member.getNotificationCount());
		}
		catch(Exception e){
			e.printStackTrace();
			return "0";
		}
	
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
			//probably means the user has never logged in to the forum, no big deal
			//e.printStackTrace();
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
		int topics = 0;
		int replies = 0;

		try{
			InputStream in = new URL("http://forum.StaticVoidGames.com/users/" + member + ".json").openStream();
			String jsonText = IOUtils.toString(in);
			IOUtils.closeQuietly(in);

			JSONObject obj = new JSONObject(jsonText);
			
			JSONArray arr = obj.getJSONObject("user").getJSONArray("stats");
			for (int i = 0; i < arr.length(); i++){
				int type = arr.getJSONObject(i).getInt("action_type");
				int count =  arr.getJSONObject(i).getInt("count");

				if(type == 1){
					likesGiven = count;
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

	@ResponseBody
	@RequestMapping(value = "wait/myNotificationCount", method = RequestMethod.GET)
	public DeferredResult<String> waitForMyNotificationCount(HttpSession session){
		
		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);
		if(loggedInMember != null){
			DeferredResult<String> dr = new DeferredResult<>();
			//LongPollingTracker.getInstance().waitForNotificationCountChange(loggedInMember, dr);
			return dr;
		}
		else{
			return null;
		}
	}
}
