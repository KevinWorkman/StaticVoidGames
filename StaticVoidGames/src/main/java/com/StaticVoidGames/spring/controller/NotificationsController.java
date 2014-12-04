package com.StaticVoidGames.spring.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.StaticVoidGames.TimestampedEvent;
import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.notifications.EventView;
import com.StaticVoidGames.notifications.Subscription;
import com.StaticVoidGames.spring.controller.interfaces.NotificationsControllerInterface;
import com.StaticVoidGames.spring.dao.MemberDao;
import com.StaticVoidGames.spring.dao.NotificationsDao;
import com.StaticVoidGames.spring.util.AttributeNames;
import com.StaticVoidGames.spring.util.OpenSourceLink;

/**
 * Controller that handles notifications.
 */
@Component
public class NotificationsController implements NotificationsControllerInterface{

	@Autowired
	private NotificationsDao notificationsDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private Environment env;

	@Override
	public String viewYourSubscriptions(ModelMap model, HttpSession session) {

		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);

		if(loggedInMember == null){
			return "login";
		}

		List<Subscription> subs = notificationsDao.getSubscriptionsForMember(loggedInMember);

		model.addAttribute("subscriptions", subs);
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/notifications/yourSubscriptions.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/NotificationsController.java")
			}
		);
		

		return "notifications/yourSubscriptions";
	}

	/**
	 * This works great locally, but the latency when accessing the live DB is unbearable.
	 */
	@Override
	public String viewYourNotifications(ModelMap model, HttpSession session) {
		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);

		if(loggedInMember == null){
			return "login";
		}

		String s3Endpoint = env.getProperty("s3.endpoint");


		long start = System.currentTimeMillis();

		List<EventView> allEventViews = new ArrayList<EventView>();
		List<Comment> allNotifications = notificationsDao.getCommentsForMember(loggedInMember);

		
		long elapsed = System.currentTimeMillis() - start;
		start = System.currentTimeMillis();

		Map<String, Member> memberMap = new HashMap<String, Member>();

		for(TimestampedEvent event : allNotifications){

			if(!memberMap.containsKey(event.getMember())){
				Member member = memberDao.getMember(event.getMember());
				memberMap.put(event.getMember(), member);
			}

			Member member = memberMap.get(event.getMember());

			if(member == null){
				continue;
			}

			EventView ev = new EventView(event, member, s3Endpoint);
			allEventViews.add(ev);
		}

		model.addAttribute("allEventViews", allEventViews);

		elapsed = System.currentTimeMillis() - start;
		start = System.currentTimeMillis();

		List<EventView> unviewedEventViews = new ArrayList<EventView>();


		List<Comment> unviewedEvents = notificationsDao.getUnviewedCommentsForMember(loggedInMember);

		for(TimestampedEvent event : unviewedEvents){
			if(!memberMap.containsKey(event.getMember())){
				Member member = memberDao.getMember(event.getMember());
				memberMap.put(event.getMember(), member);
			}

			Member member = memberMap.get(event.getMember());

			EventView ev = new EventView(event, member, s3Endpoint);
			unviewedEventViews.add(ev);
		}

		model.addAttribute("unviewedEventViews", unviewedEventViews);

		


		model.addAttribute(AttributeNames.notificationCount, notificationsDao.getUnviewedCommentsCount(loggedInMember));

		notificationsDao.setMemberViewedNotificationsTime(loggedInMember, System.currentTimeMillis());

		elapsed = System.currentTimeMillis() - start;

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/notifications/yourNotifications.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/NotificationsController.java")
			}
		);
		

		return "notifications/yourNotifications";
	}

	@Override
	public String viewSiteEvents(ModelMap model, HttpSession session) {

		String s3Endpoint = env.getProperty("s3.endpoint");


		Collection<TimestampedEvent> events = notificationsDao.getEvents(20);

		

		List<EventView> eventViews = new ArrayList<EventView>();

		for(TimestampedEvent event : events){
			Member member = memberDao.getMember(event.getMember());

			if(member == null){
				continue;
			}

			EventView ev = new EventView(event, member, s3Endpoint);
			eventViews.add(ev);
		}

		model.addAttribute("eventView", eventViews);
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/notifications/events.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/NotificationsController.java")
			}
		);

		return "notifications/events";
	}

	/**
	 * This would be used to notify the user of new notifications, but it's currently WAY too slow.
	 */
	@Override
	@RequestMapping(value="/ajaxNotificationCount", method=RequestMethod.GET)
	public @ResponseBody String getAjaxNotificationCount(HttpSession session) {

		try{
			String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);

			if(loggedInMember == null){
				return "0";
			}
			
			return String.valueOf(notificationsDao.getUnviewedCommentsCount(loggedInMember));
		}
		catch(Exception e){
			e.printStackTrace();
			return "0";
		}
	}

}
