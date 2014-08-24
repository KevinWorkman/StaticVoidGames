package com.StaticVoidGames;

/**
 * Other "events" (game uploading, member joining, blog writing, commenting) implement this interface so the Notifications DAO can lump them all together.
 */
public interface TimestampedEvent {
	
	/**
	 * Return a description of this event. X did Y.
	 */
	public String getEventText();
	
	/**
	 * Return the time (MS since epoch) that the event took place.
	 */
	public long getTimestamp();
	
	/**
	 * Return a URL relative to the top-level domain. This will be passed into a <c:url> jstl tag.
	 */
	public String getRelativeUrl();
	
	/**
	 * Return the member who did the event.
	 */
	public String getMember();
}
