package com.StaticVoidGames.comments;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.StaticVoidGames.TimestampedEvent;
import com.StaticVoidGames.spring.util.PageDownUtils;

/**
 * A single comment on either a BlogEntry, Member, or Game.
 */
@Entity
@Table( name = "Comments" )
public class Comment implements TimestampedEvent{

	/**
	 * The ID of this Comment. This is numerical and is used for deleting comments.
	 */
	@Id
	private long id;
	
	/**
	 * The member who left the comment.
	 */
	private String commentingMember;
	
	/**
	 * The ID of the thing commented on.
	 * In the case of Games, this is the game url name.
	 * In the case of BlogEntries, this is the blog url name.
	 * In the case of Members, this is the member name.
	 */
	private String thingCommentedOn;
	
	/**
	 * The type of thing that was commented on.
	 * Either GameComment, BlogComment, or AccountComment.
	 * TODO: This is only necessary in the off chance that somebody does something like create a blog with the same name as a game. We could eliminate this if we just ensured that couldn't happen.
	 */
	private String type;
	
	/**
	 * The text of the comment. This is in markdown.
	 */
	private String comment;
	
	/**
	 * The time the comment was left, in MS since epoch.
	 */
	private long timestamp;
	
	/**
	 * Constructs a new Comment with the supplied values. Sets the timestamp to the current time.
	 */
	public Comment(long id, String commentingMember, String thingCommentedOn, String comment, String type) {

		this.id = id;
		this.commentingMember = commentingMember;
		this.thingCommentedOn = thingCommentedOn;
		this.comment = comment;
		this.timestamp = System.currentTimeMillis();
		this.type = type;
	}
	
	/**
	 * Empty constructor needed by Hibernate.
	 */
	public Comment(){}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCommentingMember() {
		return commentingMember;
	}
	
	public String getMember(){
		return commentingMember;
	}

	public void setCommentingMember(String commentingMember) {
		this.commentingMember = commentingMember;
	}

	public String getThingCommentedOn() {
		return thingCommentedOn;
	}

	public void setThingCommentedOn(String thingCommentedOn) {
		this.thingCommentedOn = thingCommentedOn;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Returns: MEMBER commented on THING
	 * TODO: Return the title of the thing commented on?
	 */
	@Override
	public String getEventText() {
		return commentingMember + " commented on " + thingCommentedOn;
	}

	/**
	 * Returns the URL of the thing commented on.
	 */
	@Override
	public String getRelativeUrl() {
		
		if("GameComment".equals(type)){
			return "/games/" + thingCommentedOn;
		}
		else if("BlogComment".equals(type)){
			return "/blog/" + thingCommentedOn;
		}
		else if("AccountComment".equals(type)){
			return "/members/" + thingCommentedOn;
		}
		
		//this shouldn't happen
		return "/";
	}
}
