package com.StaticVoidGames.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.HandlerMapping;

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
	public String viewGame(HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable("game") String game, HttpSession session) {

		Game gameObj = gameDao.getGame(game);

		if(gameObj == null){
			return "redirect:/games/";
		}

		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);
		
		String s3Endpoint = env.getProperty("s3.endpoint");
		
		model.addAttribute("isOwner", gameObj.getMember().equals(loggedInMember));
		model.addAttribute("game", gameObj);
		model.addAttribute("gameDescriptionMarkdown", gameObj.getGameDescription());
		
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
		
		
		if(gameObj.getJarFileUrl() != null){
			isPlayable = true;
			model.addAttribute("isJar", true);
			//TODO jar url should only contain filename, make this relative to s3 endpoint
			if(gameObj.getJarFileUrl().startsWith("http://")){
				model.addAttribute("jarUrl", gameObj.getJarFileUrl());
			}
			else{
				model.addAttribute("jarUrl", "http://s3.staticvoidgames.com/games/" + gameObj.getGameName() + "/" + gameObj.getJarFileUrl());
			}
		}
		else{
			model.addAttribute("isJar", false);
		}
		
		
		
		if(gameObj.getSourceZipUrl() != null){
			
			
			model.addAttribute("isOpenSource", true);
			//TODO source zip url should only contain filename, make this relative to s3 endpoint
			model.addAttribute("sourceUrl", gameObj.getSourceZipUrl());
			
			if(gameObj.getSourceZipUrl().startsWith("http://")){
				model.addAttribute("sourceUrl", gameObj.getSourceZipUrl());
			}
			else{
				model.addAttribute("sourceUrl", "http://s3.staticvoidgames.com/games/" + gameObj.getGameName() + "/" +  gameObj.getSourceZipUrl());
			}
		
			model.addAttribute("sourceMarkdown", gameObj.getSourcePermissionsText());
		}
		else{
			model.addAttribute("isOpenSource", false);
		}
		
		if(gameObj.isAndroid()){
			
			isPlayable = true;
			
			model.addAttribute("isAndroid", true);
			model.addAttribute("androidText", gameObj.getAndroidText());
			
			System.out.println("APK url: " + gameObj.getApkUrl());
			
			if(gameObj.getApkUrl() != null){
				model.addAttribute("hasApk", true);
			
				
				if(gameObj.getApkUrl().startsWith("http://")){
					model.addAttribute("apkUrl", gameObj.getApkUrl());
				}
				else{
					model.addAttribute("apkUrl", "http://s3.staticvoidgames.com/games/" + gameObj.getGameName() + "/" +  gameObj.getApkUrl());
				}
				
				
			}
		}
		else{
			model.addAttribute("isAndroid", false);
		}
		
		if(gameObj.isShowLibGdxHtml()){
			isPlayable = true;
			model.addAttribute("libGdxHtmlLink", s3Endpoint + "/games/" + game + "/gdx/index.html");
		}
		
		if(gameObj.isShowProcessingJavaScript()){
			isPlayable = true;
			model.addAttribute("processingJavaScriptLink", s3Endpoint + "/games/" + game + "/p5/index.html");
		}
		
		if(gameObj.isShowJavaScript()){
			isPlayable = true;
			model.addAttribute("pureJavaScriptLink", s3Endpoint + "/games/" + game + "/js/" + gameObj.getJavaScriptIndex());
		}
		
		model.addAttribute("isPlayable", isPlayable);
		
		if(gameObj.getThumbnailUrl() != null){
						
			if(gameObj.getThumbnailUrl().startsWith("http://")){
				model.addAttribute("thumbnailImage", gameObj.getThumbnailUrl());
			}
			else{
				model.addAttribute("thumbnailImage", s3Endpoint + "/games/" + gameObj.getGameName() + "/" +  gameObj.getThumbnailUrl());
			}
		}
		else{
			model.addAttribute("thumbnailImage", s3Endpoint + "/images/staticVoidProfile3.png");
		}
		
		if(gameObj.getFaviconUrl() != null){
			
			if(gameObj.getFaviconUrl().startsWith("http://")){
				model.addAttribute("faviconImage", gameObj.getFaviconUrl());
			}
			else{
				model.addAttribute("faviconImage", s3Endpoint + "/games/" + gameObj.getGameName() + "/" +  gameObj.getFaviconUrl());
			}
		}
		else{
			model.addAttribute("faviconImage", "http://StaticVoidGames.com/images/favicon.png");
		}
		
		if(gameObj.getBackgroundUrl() != null){
			if(gameObj.getBackgroundUrl().startsWith("http://")){
				model.addAttribute("backgroundImage", gameObj.getBackgroundUrl());
			}
			else{
				model.addAttribute("backgroundImage", s3Endpoint + "/games/" + gameObj.getGameName() + "/" +  gameObj.getBackgroundUrl());
			}
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
	@RequestMapping(value = {"/{game}/{subdirectory}/**"}, method = RequestMethod.GET)
	public String viewGameHtmlFile(HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable("game") String game, @PathVariable("subdirectory") String subdirectory, HttpSession session) {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		System.out.println("View game html file.");
		
		// /games/gameName/html/blah/blah
		String url = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		url = url.replace("/games/"+game, "");
		
		System.out.println("Rest of url: " + url);
		
		String s3File = "http://s3.staticvoidgames.com/games/" + game + "/libGdxHtml5/dist" + url;
		
		//String s3File = "http://localhost:8080/StaticVoidGames/tutorialsContent/dist" + url;
		
		System.out.println("s3 file: " + s3File);
		
	//	response.setHeader("Location", s3File);
		
		return "redirect:" + s3File;
		
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
		
		//new game page already contains mothball message
		return "editGame/new";
		
//		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);
//		
//		if(loggedInMember == null){
//			return "login";
//		}
//		
//		if(gameUrlName.contains("/") || gameUrlName.contains("\\")){
//			
//		}
//		
//		Game g = gameDao.getGame(gameUrlName);
//		if(g == null){
//			
//			g = new Game(gameUrlName, loggedInMember);
//			
//			gameDao.updateGame(g);
//			
//			notificationDao.updateMemberSubscription(loggedInMember, gameUrlName, "Game", "Comments on " + gameUrlName);
//			
//			return "redirect:/games/" + gameUrlName + "/edit/";
//		}
//		else{
//			model.addAttribute("newError", "A game with that URL already exists.");
//			
//			return "editGame/new";
//		}
	}
}
