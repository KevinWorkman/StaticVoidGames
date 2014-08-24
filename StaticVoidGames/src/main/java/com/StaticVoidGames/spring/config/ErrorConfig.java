package com.StaticVoidGames.spring.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class will catch any Exceptions thrown by a controller and redirect.
 * TODO: This doesn't actually do much. Set up some error pages.
 */
@ControllerAdvice
public class ErrorConfig{

	@ExceptionHandler(Exception.class)
	public String handleException () {
		System.out.println("ERROR");
		return "index";
	}
}
