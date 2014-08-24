package com.StaticVoidGames.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.StaticVoidGames.blog.BlogEntry;
import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.games.Game;
import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.spring.controller.interfaces.CommentControllerInterface;
import com.StaticVoidGames.spring.dao.BlogEntryDao;
import com.StaticVoidGames.spring.dao.CommentDao;
import com.StaticVoidGames.spring.dao.GameDao;
import com.StaticVoidGames.spring.dao.MemberDao;
import com.StaticVoidGames.spring.dao.NotificationsDao;
import com.StaticVoidGames.spring.util.AttributeNames;

/**
 * Controller that handles creating and deleting comments.
 */
@Component
public class CommentController implements CommentControllerInterface{

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private BlogEntryDao blogEntryDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private NotificationsDao notificationDao;

	@Transactional
	public String deleteComment(String id,  HttpSession session) {

		Comment comment = commentDao.getComment(Long.valueOf(id));

		if(comment == null){
			return "redirect:/";
		}

		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);
		if(loggedInMember == null){
			return "redirect:/";
		}
		boolean ownsComment = loggedInMember.equals(comment.getCommentingMember());

		String redirect = "/";

		boolean valid;

		String type = comment.getType();
		String thingCommentedOn = comment.getThingCommentedOn();
		if("AccountComment".equals(type)){

			Member member = memberDao.getMember(thingCommentedOn);

			if(member == null){
				redirect = "/members/";
				valid = false;
			}
			else if(!ownsComment && !loggedInMember.equals(member.getMemberName())){
				redirect = "/members/" + thingCommentedOn;
				valid = false;
			}
			else{
				redirect = "/members/" + thingCommentedOn;
				valid = true;
			}
		}
		else if("BlogComment".equals(type)){

			BlogEntry blog = blogEntryDao.getBlogEntry(thingCommentedOn);

			if(blog == null){
				redirect = "/blog/";
				valid = false;
			}
			else if(!ownsComment && !loggedInMember.equals(blog.getMember())){
				redirect =  "/blog/" + blog.getMember() + "/" + thingCommentedOn;
				valid = false;
			}
			else{
				redirect =  "/blog/" + blog.getMember() + "/" + thingCommentedOn;
				valid = true;
			}
		}
		else if("GameComment".equals(type)){

			Game game = gameDao.getGame(thingCommentedOn);

			if(game == null){
				redirect = "/games/";
				valid = false;
			}
			else if(!ownsComment && !loggedInMember.equals(game.getMember())){
				redirect = "/games/" + thingCommentedOn;
				valid = false;
			}
			else{
				redirect = "/games/" + thingCommentedOn;
				valid = true;
			}
		}
		else{
			valid = false;
		}

		if(valid){
			commentDao.deleteComment(comment);
		}

		return "redirect:" + redirect;
	}


	@Transactional
	public String addComment(@PathVariable(value="type") String type, @PathVariable(value="thingCommentedOn") String thingCommentedOn, @RequestParam("commentInput") String commentInput, HttpSession session){

		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);

		if(loggedInMember != null){
			Comment comment = new Comment(commentDao.getNextId(), loggedInMember, thingCommentedOn, commentInput, type);
			commentDao.addComment(comment);
		}


		//TODO check if thingCommentedOn is valid?

		if("AccountComment".equals(type)){
			notificationDao.updateMemberSubscription(loggedInMember, thingCommentedOn, "Account", "Comments on " + thingCommentedOn);
			return "redirect:/members/" + thingCommentedOn;
		}
		else if("BlogComment".equals(type)){
			
			notificationDao.updateMemberSubscription(loggedInMember, thingCommentedOn, "Blog", "Comments on " + thingCommentedOn);
	
			BlogEntry blog = blogEntryDao.getBlogEntry(thingCommentedOn);
			
			notificationDao.updateMemberSubscription(loggedInMember, thingCommentedOn, "Blog", "Comments on " + blog.getTitle());
		
			return "redirect:/blog/" + thingCommentedOn;
		}
		else if("GameComment".equals(type)){
			
			notificationDao.updateMemberSubscription(loggedInMember, thingCommentedOn, "Game", "Comments on " + thingCommentedOn);
			
			return "redirect:/games/" + thingCommentedOn;
		}

		return "redirect:/";
	}
}
