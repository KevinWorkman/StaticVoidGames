package com.StaticVoidGames.blog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.StaticVoidGames.members.Member;

/**
 * This class encapsulates the information needed to create a thumbnail for a blog entry.
 * There might be a smarter way to do this, but for now this works.
 */
public class BlogView {
	
	private BlogEntry blog;
	private Member member;
	private String s3Endpoint;
	
	public BlogView(BlogEntry blog, Member member, String s3Endpoint){
		this.blog = blog;
		this.member = member;
		this.s3Endpoint = s3Endpoint;
	}
	
	public BlogEntry getBlog(){
		return blog;
	}
	
	public String getFormattedDate(){
		DateFormat df = new SimpleDateFormat("MMMM dd, YYYY");
		return df.format(new Date(blog.getTimestamp()));
	}
	
	public String getMemberPictureUrl(){
		return s3Endpoint + "/users/" + member.getMemberName() + "/" + member.getProfileImageUrl(); 
	}
}
