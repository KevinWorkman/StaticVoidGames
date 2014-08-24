package com.StaticVoidGames.notifications;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class encapsulates a member subscription to an event. This causes the member to receive notifications for new comments.
 */
@Entity
@Table( name = "Subscriptions" )
public class Subscription {

	/**
	 * The ID of this Subscription
	 */
	@Id
	long id;

	/**
	 * The member who subscribed to this event.
	 */
	private String member;
	
	/**
	 * The ID of the thing subscribed to.
	 * For BlogEntries or Games, this is the urlName. For Members, this is the member name.
	 */
	private String entityId;
	
	/**
	 * The type of the thing subscribed to. BlogEntry or Game or Member.
	 */
	private String type;
	
	/**
	 * The label for this subscription. "Comments on XYZ"
	 * TODO: this is meant to be used for managing subscription settings.
	 */
	private String label;
	
	/**
	 * The last time the user saw the notifications for this Subscription.
	 * Any event in this subscription after this time is a new notification.
	 */
	private long lastNotificationViewedTimestamp;
	
	/**
	 * The time this Subscription was created.
	 */
	private long timestamp;
	
	/**
	 * Constructs a new Subscription and sets the timestamp to the current time.
	 */
	public Subscription(){
		this.timestamp = System.currentTimeMillis();
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getLastNotificationViewedTimestamp() {
		return lastNotificationViewedTimestamp;
	}

	public void setLastNotificationViewedTimestamp(
			long lastNotificationViewedTimestamp) {
		this.lastNotificationViewedTimestamp = lastNotificationViewedTimestamp;
	}
	
	public void setTimestamp(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp(){
		return timestamp;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId(){
		return id;
	}
}
