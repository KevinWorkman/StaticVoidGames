package com.StaticVoidGames.spring.util;

/**
 * This was meant to be a centralized place to store attibute names passed between controllers and jsp, but its use is sporadic.
 */
public interface AttributeNames {
	public static final String lastPageVisited = "loginUrl";
	public static final String isLoggedIn = "isLoggedIn";
	public static final String loggedInUser = "loggedInUser";
	public static final String escapedLoggedInUser = "escapedLoggedInUser";
	public static final String urlEscapedLoggedInUser = "urlEscapedLoggedInUser";
	public static final String s3Endpoint = "s3Endpoint";
	public static final String notificationCount = "notificationCount";
}
