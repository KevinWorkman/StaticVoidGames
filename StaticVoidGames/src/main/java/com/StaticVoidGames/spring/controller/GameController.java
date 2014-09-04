package com.StaticVoidGames.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.comments.CommentView;
import com.StaticVoidGames.games.Game;
import com.StaticVoidGames.games.GameExecutable;
import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.spring.controller.interfaces.GameControllerInterface;
import com.StaticVoidGames.spring.dao.CommentDao;
import com.StaticVoidGames.spring.dao.GameDao;
import com.StaticVoidGames.spring.dao.MemberDao;
import com.StaticVoidGames.spring.dao.NotificationsDao;
import com.StaticVoidGames.spring.util.AttributeNames;
import com.StaticVoidGames.spring.util.OpenSourceLink;

/**
 * Controller that handles game pages, other than the edit pages.
 */
@Component
public class GameController implements GameControllerInterface{

	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private NotificationsDao notificationDao;
	
	@Override
	@Transactional
	@RequestMapping("")
	public String listGames(HttpServletRequest request, ModelMap model, HttpSession session) {
		
		List<Game> games = gameDao.getAllPublishedGames();
		session.setAttribute("games", games);
		model.addAttribute("count", games.size());
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/games/listGames.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/GameController.java")
			}
		);
		
		return "/games/listGames";
	}
	
	@Override
	@Transactional
	@RequestMapping(value = {"/{game}", "play/?game={game}"}, method = RequestMethod.GET)
	public String viewGame(HttpServletRequest request, ModelMap model, @PathVariable("game") String game, HttpSession session) {

		Game gameObj = gameDao.getGame(game);

		if(gameObj == null){
			return "redirect:/games/";
		}

		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);
		
		String s3Endpoint = env.getProperty("s3.endpoint");
		
		model.addAttribute("isOwner", gameObj.getMember().equals(loggedInMember));
		model.addAttribute("game", gameObj);
		
		boolean isPlayable = false;
		
		List<GameExecutable> gameExecutables = gameDao.getGameExecutables(game);
		if(gameExecutables.isEmpty()){
			model.addAttribute("hasExecutables", false);
		}
		else{
			isPlayable = true;
			model.addAttribute("hasExecutables", true);
			model.addAttribute("gameExecutables", gameExecutables);
		}
		
		if(gameObj.getAppletClass() != null){
			isPlayable = true;
			model.addAttribute("isApplet", true);
			model.addAttribute("appletJnlpUrl", s3Endpoint + "/games/" + game + "/" + gameObj.getGameName() + "Applet.jnlp");
		}
		else{
			model.addAttribute("isApplet", false);
		}
		
		if(gameObj.getMainClass() != null){
			isPlayable = true;
			model.addAttribute("isWebstart", true);
			model.addAttribute("webstartJnlpUrl", s3Endpoint + "/games/" + game + "/" + gameObj.getGameName() + "WebStart.jnlp");
		}
		else{
			model.addAttribute("isWebstart", false);
		}
		
		if(!gameObj.isLwjgl() && gameObj.getJarFileUrl() != null && gameDao.getGameLibFiles(game).isEmpty() ){
			isPlayable = true;
			model.addAttribute("isJar", true);
			//TODO jar url should only contain filename, make this relative to s3 endpoint
			model.addAttribute("jarUrl", gameObj.getJarFileUrl());
		}
		else{
			model.addAttribute("isJar", false);
		}
		
		if(gameObj.getSourceZipUrl() != null){
			model.addAttribute("isOpenSource", true);
			//TODO source zip url should only contain filename, make this relative to s3 endpoint
			model.addAttribute("sourceUrl", gameObj.getSourceZipUrl());
			model.addAttribute("sourceText", gameObj.getSourcePermissionsText());
		}
		else{
			model.addAttribute("isOpenSource", false);
		}
		
		if(gameObj.isAndroid()){
			model.addAttribute("isAndroid", true);
			model.addAttribute("androidText", gameObj.getAndroidText());
			
			if(gameObj.getApkUrl() != null){
				model.addAttribute("hasApk", true);
				model.addAttribute("apkUrl", s3Endpoint + "/games/" + gameObj.getGameName() + "/" + gameObj.getApkUrl());
			}
		}
		else{
			model.addAttribute("isAndroid", false);
		}
		
		
		model.addAttribute("isPlayable", isPlayable);
		
		if(gameObj.getThumbnailUrl() != null){
			model.addAttribute("thumbnailImage", s3Endpoint + "/games/" + game + "/" + gameObj.getThumbnailUrl());
		}
		else{
			//TODO generic thumbnail
		}
		
		if(gameObj.getFaviconUrl() != null){
			model.addAttribute("faviconImage", s3Endpoint + "/games/" + game + "/" + gameObj.getFaviconUrl());
		}
		
		if(gameObj.getBackgroundUrl() != null){
			model.addAttribute("backgroundImage", s3Endpoint + "/games/" + game + "/" + gameObj.getBackgroundUrl());
		}
		
		List<CommentView> commentViews = new ArrayList<CommentView>();
		for(Comment c : commentDao.getComments(game, "GameComment")){
			Member member = memberDao.getMember(c.getCommentingMember());
			
			CommentView cv = new CommentView(c, member, s3Endpoint);
			commentViews.add(cv);
		}
		
		model.addAttribute("commentViews", commentViews);
		
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/games/viewGame.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/GameController.java")
			}
		);

		return "games/viewGame";
	}

	@Override
	public String newGame(ModelMap model, HttpSession session) {
		
		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);
		
		if(loggedInMember == null){
			return "login";
		}
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/editGame/new.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/GameController.java")
			}
		);
		
		return "editGame/new";
	}


	@Override
	public String newGamePost(ModelMap model, HttpSession session, @RequestParam("gameUrlName") String gameUrlName) {
		
		
		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);
		
		if(loggedInMember == null){
			return "login";
		}
		
		Game g = gameDao.getGame(gameUrlName);
		if(g == null){
			
			g = new Game(gameUrlName, loggedInMember);
			
			gameDao.updateGame(g);
			
			notificationDao.updateMemberSubscription(loggedInMember, gameUrlName, "Game", "Comments on " + gameUrlName);
			
			return "redirect:/games/" + gameUrlName + "/edit/";
		}
		else{
			model.addAttribute("newError", "A game with that URL already exists.");
			
			return "editGame/new";
		}
	}
}
