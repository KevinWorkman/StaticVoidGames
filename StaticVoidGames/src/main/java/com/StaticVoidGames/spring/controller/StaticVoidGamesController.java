package com.StaticVoidGames.spring.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.members.MemberPointsView;
import com.StaticVoidGames.spring.controller.interfaces.StaticVoidGamesControllerInterface;
import com.StaticVoidGames.spring.dao.BlogEntryDao;
import com.StaticVoidGames.spring.dao.MemberDao;
import com.StaticVoidGames.spring.dao.NotificationsDao;
import com.StaticVoidGames.spring.util.ActivationEmailSender;
import com.StaticVoidGames.spring.util.AttributeNames;
import com.StaticVoidGames.spring.util.OpenSourceLink;

/**
 * Controller that handles miscellaneous pages: the front page, login, registration, etc.
 */
@Component
public class StaticVoidGamesController implements StaticVoidGamesControllerInterface{

	@Autowired
	private Environment env;

	@Autowired
	private NotificationsDao notificationsDao;

	@Autowired
	private BlogEntryDao blogDao;

	@Autowired
	private MemberDao memberDao;
	

	@Override
	@Transactional
	public String viewHomePage(HttpServletRequest request, ModelMap model, HttpSession session){

		model.addAttribute("events", notificationsDao.getEvents(15));
		model.addAttribute("latestBlog", blogDao.getBlogsOfMember("Kevin").get(0));

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/index.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/StaticVoidGamesController.java")
		}
				);

		return "index";
	}

	@Override
	@RequestMapping(value = "/login")
	public String login(ModelMap model, HttpSession session) {

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/login.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/StaticVoidGamesController.java")
		}
				);

		return "login";
	}

	@Override
	@RequestMapping(value = "/logout-success", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session) {

		if(session.getAttribute(AttributeNames.lastPageVisited) != null){
			return "redirect:" + session.getAttribute(AttributeNames.lastPageVisited);
		}
		else{
			return "redirect:" + "/";
		}
	}

	@Override
	public String siteMap(ModelMap model, HttpSession session) {
		return "siteMap";
	}

	/**
	 * Experimental.
	 */
	@Transactional
	@Override
	public String points(ModelMap model, HttpSession session) throws Exception {

		List<Member> members = memberDao.getAllMembers();

		List<MemberPointsView> mpvs = new ArrayList<MemberPointsView>();

		for(Member m : members){
			int points = memberDao.getPoints(m.getMember());
			MemberPointsView mpv = new MemberPointsView(m, points);
			mpvs.add(mpv);
		}

		Collections.sort(mpvs, new Comparator<MemberPointsView>(){

			@Override
			public int compare(MemberPointsView o1, MemberPointsView o2) {
				return Integer.valueOf(o2.getPoints()).compareTo(o1.getPoints());
			}});

		model.addAttribute("memberPointsViews", mpvs);

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/standings.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/StaticVoidGamesController.java")
		}
				);

		return "points";
	}

	@Override
	public String register(ModelMap model, HttpSession session) {


		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/register.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/StaticVoidGamesController.java")
		}
				);


		String recaptchaPublicKey = env.getProperty("recaptcha.publicKey");
		String recaptchaPrivateKey = env.getProperty("recaptcha.privateKey");

		ReCaptcha c = ReCaptchaFactory.newReCaptcha(recaptchaPublicKey, recaptchaPrivateKey, false);
		model.addAttribute("recaptchaHtml", c.createRecaptchaHtml(null, null));

		return "register";
	}

	@Transactional
	@Override
	public String registerPost(HttpServletRequest request, ModelMap model, HttpSession session, String email, String username, String password) {


		Member m = memberDao.getMember(username);
		
		model.addAttribute("username", username);
		model.addAttribute("email", email);

		if(m != null){
			model.addAttribute("registrationError", "Somebody already took that username.");
			return register(model, session);
		}


		String recaptchaPrivateKey = env.getProperty("recaptcha.privateKey");

		String remoteAddr = request.getRemoteAddr();
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey(recaptchaPrivateKey);

		String challenge = request.getParameter("recaptcha_challenge_field");
		String uresponse = request.getParameter("recaptcha_response_field");
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

		if (!reCaptchaResponse.isValid()) {
			model.addAttribute("registrationError", "Please try the captcha again.");
			return register(model, session);
		}

		m = new Member();
		m.setJoinTimestamp(System.currentTimeMillis());
		m.setMemberName(username);
		m.setEmail(email);

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(10);
		String bcryptHash = bcrypt.encode(password);

		m.setBcryptHash(bcryptHash);
		
		m.setActivationId(ActivationEmailSender.generateRandomKey(32));

		memberDao.updateMember(m);
		
		notificationsDao.updateMemberSubscription(username, username, "Account", "Comments on your profile");
		
		ActivationEmailSender aes = new ActivationEmailSender(env);
		aes.sendActivationEmail(m);
		
		model.addAttribute("loginError", "Before you can login, you must activate your account by clicking the link in the activation email.");

		return "login";

	}

	@Override
	public String oldLogin(ModelMap model, HttpSession session) {

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/oldLogin.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/StaticVoidGamesController.java")
		}
				);

		return "oldLogin";
	}

	@Transactional
	@Override
	public String oldLoginPost(ModelMap model, HttpSession session, String username, String password) {

		Member m = memberDao.getMember(username);
		if(m == null){
			model.addAttribute("oldLoginError", "Couldn't find a user with that username.");
			return "/oldLogin";
		}


		boolean isValid = (m.getPasswordHash() == password.hashCode());

		if(isValid){
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(10);
			String bcryptHash = bcrypt.encode(password);

			m.setBcryptHash(bcryptHash);
			memberDao.updateMember(m);
			return "redirect:/login";
		}
		else{
			model.addAttribute("oldLoginError", "That password was incorrect.");
			return "/oldLogin";
		}
	}
}
