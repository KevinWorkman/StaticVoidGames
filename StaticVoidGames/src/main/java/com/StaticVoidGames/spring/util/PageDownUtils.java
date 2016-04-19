package com.StaticVoidGames.spring.util;

import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;

/**
 * Utility class that contains a method that parses markdown into html.
 */
public class PageDownUtils {

	/**
	 * Parses the supplied markdown into html.
	 * This calls the PageDown javascript library to do the work for us.
	 */
	public static String getSanitizedHtml(String markdown){
		
//		System.out.println(new File(".").getAbsolutePath());
//		for(String f : new File(".").list()){
//			System.out.println("\t" + f);
//		}

		if(markdown == null){
			return null;
		}

		try{
			V8 runtime = V8.createV8Runtime();
			
			 runtime.executeScript( new String(Files.readAllBytes(Paths.get(PageDownUtils.class.getResource("Markdown.Converter.js").toURI()))));
	         runtime.executeScript( new String(Files.readAllBytes(Paths.get(PageDownUtils.class.getResource("Markdown.Sanitizer.js").toURI()))));
	         runtime.executeScript( new String(Files.readAllBytes(Paths.get(PageDownUtils.class.getResource("MarkdownParser.js").toURI()))));

			V8Array args = new V8Array(runtime);
			args.push(markdown);

			String html = runtime.executeStringFunction("parseMarkdown", args);
			args.release();
			runtime.release();
			return html;
		}
		catch(Exception e){
			e.printStackTrace();
			return "ERROR";
		}
	}
}
