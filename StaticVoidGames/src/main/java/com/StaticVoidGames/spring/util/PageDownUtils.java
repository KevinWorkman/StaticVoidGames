package com.StaticVoidGames.spring.util;

import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Utility class that contains a method that parses markdown into html.
 */
public class PageDownUtils {

	/**
	 * Parses the supplied markdown into html.
	 * This calls the PageDown javascript library to do the work for us.
	 */
	public static String getSanitizedHtml(String markdown){
		
		if(markdown == null){
			return null;
		}

		//TODO: going through the javascript library seems a bit hackish. If there's a PageDown Java library, we should switch to that.
		//The other way to go would be to eliminate this altogether and do all of the PageDown parsing on the client side.
		
		try{
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			engine.eval(new InputStreamReader(PageDownUtils.class.getResourceAsStream("pageDown.js")));
			Invocable inv = (Invocable) engine;
			String s = String.valueOf(inv.invokeFunction("getSanitizedHtml", markdown));

			return s;
		}
		catch(Exception e){
			e.printStackTrace();
			return "ERROR";
		}
	}
}
