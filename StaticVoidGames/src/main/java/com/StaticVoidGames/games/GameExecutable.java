package com.StaticVoidGames.games;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.StaticVoidGames.spring.util.PageDownUtils;

/**
 * This class represents a single packaged executable file for a game. A game can have more than one of these, but most games will have none.
 * TODO: there is currently no way to create these. This hasn't been implemented in the new site yet.
 */
@Entity
@Table( name = "GameExecutables" )
public class GameExecutable {

	@Id
	private long id;
	
	private long timestamp;
	
	private String game;
	
	private String label;
	
	private String url;
	
	public GameExecutable(String game, String label, String url){
	
		this.game = game;
		this.label = label;
		this.url = url;
		
		this.timestamp = System.currentTimeMillis();
		
	}
	
	/**
	 * Empty constructor needed by Hibernate.
	 */
	public GameExecutable(){}
	
	public String getParsedLabel(){
		return PageDownUtils.getSanitizedHtml(label);
	}
	
	public String getLabel(){
		return label;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public long getId(){
		return id;
	}

	public String getGame() {
		return game;
	}
}
