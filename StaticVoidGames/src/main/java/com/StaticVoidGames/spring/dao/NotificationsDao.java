package com.StaticVoidGames.spring.dao;

import java.util.Collection;
import java.util.List;

import com.StaticVoidGames.TimestampedEvent;
import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.notifications.Subscription;

public interface NotificationsDao {

	public int getUnviewedCommentsCount(String user);
    public List<Comment> getCommentsForSubscriptions(Iterable<Subscription> subscriptions);
	public List<Subscription> getSubscriptionsForMember(String member);
	public List<Comment> getCommentsForMember(String member);
	public List<Comment> getUnviewedCommentsForMember(String member);
	public Collection<TimestampedEvent> getAllEvents();
	public Collection<TimestampedEvent> getEvents(int count);
	public void setMemberViewedNotificationsTime(String loggedInMember, long time);
	void updateMemberSubscription(String member, String entityId, String type, String label);
	public List<Subscription> getAllSubscriptions();
}
