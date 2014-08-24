package com.StaticVoidGames.comments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.StaticVoidGames.members.Member;

/**
 * This class encapsulates the information needed to show a member's profile thumbnail next to a comment.
 */
public class CommentView {
	private Comment comment;
	private Member member;
	private String s3Endpoint;
	
	public CommentView(Comment comment, Member member, String s3Endpoint){
		this.comment = comment;
		this.member = member;
		this.s3Endpoint = s3Endpoint;
	}
	
	public Comment getComment(){
		return comment;
	}
	
	public Member getMember(){
		return member;
	}
	
	public String getFormattedDate(){
		DateFormat df = new SimpleDateFormat("MMMM dd, YYYY");
		return df.format(new Date(comment.getTimestamp()));
	}
	
	public String getMemberPictureUrl(){
		if(member.getProfileImageUrl() == null){
			return null;
		}
		return s3Endpoint + "/users/" + member.getMemberName() + "/" + member.getProfileImageUrl(); 
	}
}
