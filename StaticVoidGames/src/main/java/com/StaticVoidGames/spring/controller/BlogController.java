package com.StaticVoidGames.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.StaticVoidGames.blog.BlogEntry;
import com.StaticVoidGames.blog.BlogView;
import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.comments.CommentView;
import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.spring.controller.interfaces.BlogControllerInterface;
import com.StaticVoidGames.spring.dao.BlogEntryDao;
import com.StaticVoidGames.spring.dao.CommentDao;
import com.StaticVoidGames.spring.dao.MemberDao;
import com.StaticVoidGames.spring.dao.NotificationsDao;
import com.StaticVoidGames.spring.util.AttributeNames;
import com.StaticVoidGames.spring.util.OpenSourceLink;

/**
 * Controller that handles blogs.
 */
@Component
public class BlogController implements BlogControllerInterface{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private BlogEntryDao blogEntryDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private NotificationsDao notificationDao;

	@Override
	@Transactional
	@RequestMapping("/blog/")
	public String listBlogs(HttpServletRequest request, ModelMap model, HttpSession session) {
		
		String s3Endpoint = env.getProperty("s3.endpoint");
		
		List<BlogEntry> blogs =  blogEntryDao.getAllBlogsByTime();
		
		List<BlogView> blogViews = new ArrayList<BlogView>();
		
		Map<String, Member> memberMap = new HashMap<String, Member>();
		
		for(BlogEntry blog : blogs){
			
			if(!memberMap.containsKey(blog.getMember())){
				Member member = memberDao.getMember(blog.getMember());
				memberMap.put(member.getMember(), member);
			}
			
			Member member = memberMap.get(blog.getMember());
			
			BlogView blogView = new BlogView(blog, member, s3Endpoint);
			
			blogViews.add(blogView);
		}
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/blog/listBlogs.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/BlogController.java")
				}
		);
		
		
		model.addAttribute("blogViews", blogViews);
		return "/blog/listBlogs";
	}

	@Override
	@Transactional
	@RequestMapping("/{blogUrlId}")
	public String viewBlogEntry(HttpServletRequest request, ModelMap model,
			@PathVariable("blogUrlId") String blogUrlId, HttpSession session) {
		
		System.out.println("HERE");
		
		String s3Endpoint =  env.getProperty("s3.endpoint");
		
		BlogEntry blog = blogEntryDao.getBlogEntry(blogUrlId);
		model.addAttribute("blog", blog);
		
		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);
		model.addAttribute("isOwner", blog.getMember().equals(loggedInMember));
		model.addAttribute("s3Endpoint", s3Endpoint);
		
		List<CommentView> commentViews = new ArrayList<CommentView>();
		for(Comment c : commentDao.getComments(blogUrlId, "BlogComment")){
			Member member = memberDao.getMember(c.getCommentingMember());
			
			CommentView cv = new CommentView(c, member, s3Endpoint);
			commentViews.add(cv);
		}
		
		model.addAttribute("commentViews", commentViews);
		
		
		return "/blog/viewBlog";
	}

	@Override
	@Transactional
	public String editBlogEntry(HttpServletRequest request, ModelMap model, @PathVariable("blogUrlId") String blogUrlId, HttpSession session) {
		
		BlogEntry blog = blogEntryDao.getBlogEntry(blogUrlId);
		
		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);
		if(!blog.getMember().equals(loggedInMember)){
			return "redirect:"+"/blog/" + blog.getUrlName();
		}
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/blog/editBlog.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/BlogController.java")
				}
		);
		
		model.addAttribute("blog", blog);
		model.addAttribute("s3Endpoint", env.getProperty("s3.endpoint"));
		
		return "/blog/editBlog";
	}

	@Override
	public String editBlogEntrySubmit(HttpServletRequest request, ModelMap model, HttpSession session) {
		
		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);

		String editedBlog = request.getParameter("editedBlog");
		
		BlogEntry blog = blogEntryDao.getBlogEntry(editedBlog);

		if(!loggedInMember.equals(blog.getMember())){
			return "redirect:" + BlogController.getFullUrl(blog);
		}
		
		String title = request.getParameter("title");
		blog.setTitle(title);
		blogEntryDao.updateBlogEntry(blog);

		return "redirect:" + BlogController.getFullUrl(blog);
		
	}
	
	@Transactional
	@RequestMapping(value="/new", method = RequestMethod.GET)
    public String newBlogEntry(ModelMap model){	
		
		
		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/blog/new.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/BlogController.java")
				}
		);
		
		return "/blog/new";
	}

	@Transactional
	@RequestMapping(value="/new", method = RequestMethod.POST)
    public String newBlogEntryPost(HttpSession session, @RequestParam("title") String title, @RequestParam("text") String text){
	
		boolean isLoggedIn = (Boolean) session.getAttribute(AttributeNames.isLoggedIn);
		
		if(!isLoggedIn){
			return "redirect:/login";
		}
		
		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);
		
		String blogTitle = WordUtils.capitalize(title);
		
		String urlOrig = blogTitle.replaceAll("[^a-zA-Z0-9]", "");
		String url = urlOrig;
		int i = 0;
		while(blogEntryDao.doesBlogEntryExist(url)){
			i++;
			url = urlOrig + "_" +  i;
		}
		
		BlogEntry blog = new BlogEntry(url, loggedInMember, null, title, text);
		blogEntryDao.addBlogEntry(blog);
		
		notificationDao.updateMemberSubscription(loggedInMember, url, "Blog", "Comments on " + blogTitle);
		
		return "redirect:/blog/" + url;
	}

	public static String getFullUrl(BlogEntry blog){
		return "/blog/"+ blog.getUrlName();
	}	
}
