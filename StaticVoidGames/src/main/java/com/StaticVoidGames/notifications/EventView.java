package com.StaticVoidGames.notifications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.StaticVoidGames.TimestampedEvent;
import com.StaticVoidGames.members.Member;

/**
 * This class encapsulates the information needed to show a member's profile next to an event (BlogEntry, Game, Comment, etc)
 */
public class EventView {
	TimestampedEvent event;
	Member member;
	String s3Endpoint;
	
	public EventView(TimestampedEvent event, Member member, String s3Endpoint){
		this.event = event;
		this.member = member;
		this.s3Endpoint = s3Endpoint;
	}
	
	public TimestampedEvent getEvent(){
		return event;
	}
	
	public Member getMember(){
		return member;
	}
	
	public String getFormattedDate(){
		DateFormat df = new SimpleDateFormat("MMMM dd, YYYY");
		return df.format(new Date(event.getTimestamp()));
	}
	
	public String getMemberPictureUrl(){
		if(member.getProfileImageUrl() == null){
			return null;
		}
		return s3Endpoint + "/users/" + member.getMemberName() + "/" + member.getProfileImageUrl(); 
	}
}
