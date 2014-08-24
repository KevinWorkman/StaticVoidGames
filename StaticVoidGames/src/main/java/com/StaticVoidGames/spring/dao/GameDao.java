package com.StaticVoidGames.spring.dao;

import java.util.List;

import com.StaticVoidGames.games.Game;
import com.StaticVoidGames.games.GameExecutable;

public interface GameDao {
	public List<Game> getPublishedGamesOfMember(String member);
	public Game getGame(String game);
	public List<Game> getUnpublishedGamesOfMember(String member);
	public void updateGame(Game gameObj);
	public List<Game> getAllPublishedGames();
	public List<GameExecutable> getGameExecutables(String game);
	public List<String> getGameLibFiles(String game);
}
