package com.StaticVoidGames.spring.util;

import java.io.UnsupportedEncodingException;

import org.owasp.encoder.Encode;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.UriUtils;

public class HtmlEscaper {
	
	public static void main(String... args){
		String str = "email@gmail.com";
		str = escapeUrlPart(str);
		System.out.println(str);
	}
	
	/**
	 * Escapes html so <tags> don't get included.
	 * <a href="blah">ESCAPE THIS</a>
	 */
	public static String escape(String html){
		if(html == null){
			return null;
		}
		
		return HtmlUtils.htmlEscape(html);
	}
	
	public static String unescape(String html){
		if(html == null){
			return null;
		}
		
		return HtmlUtils.htmlUnescape(html);
	}
	
	/**
	 * Escapes a url part so bad characters (/<.space) don't get included.
	 * <a href="StaticVoidGames.com/example/ESCAPE THIS">blah</a>
	 */
	public static String escapeUrlPart(String part){
		if(part == null){
			return null;
		}
		
		return Encode.forUriComponent(part);
		
//		try {
//			return UriUtils.encodePathSegment(part, "UTF-8");
//		} 
//		catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		//return null;
	}
	
	/**
	 * Unescapes an already escaped url part so the server can query the original value
	 */
	public static String unescapeUrlPart(String part){
		if(part == null){
			return null;
		}
		
		try {
			return UriUtils.decode(part, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
