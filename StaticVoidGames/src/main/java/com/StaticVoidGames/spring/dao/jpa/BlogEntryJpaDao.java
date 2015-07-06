package com.StaticVoidGames.spring.dao.jpa;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StaticVoidGames.blog.BlogEntry;
import com.StaticVoidGames.spring.dao.BlogEntryDao;

@Service
public class BlogEntryJpaDao implements BlogEntryDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addBlogEntry(BlogEntry blog) {
		sessionFactory.getCurrentSession().save(blog);
		
	}

	@Override
	public void deleteBlogEntry(BlogEntry blog) {
		sessionFactory.getCurrentSession().delete(blog);
	}

	@Override
	public List<BlogEntry> getAllBlogsByTime() {
		List<BlogEntry> blogs =(List<BlogEntry>) sessionFactory.getCurrentSession().createCriteria(BlogEntry.class)
				.addOrder(Order.desc("timestamp"))
				.list();
		return blogs;
	}

	@Override
	public Set<String> getBlogAuthors() {
		Set<String> authors = new LinkedHashSet<String>();

		List<BlogEntry> allBlogs = getAllBlogsByTime();
		for(BlogEntry b : allBlogs){
			authors.add(b.getMember());
		}

		return authors;
	}

	@Override
	public Set<String> getBloggedGames() {
		Set<String> games = new LinkedHashSet<String>();

		List<BlogEntry> allBlogs = getAllBlogsByTime();
		for(BlogEntry b : allBlogs){
			if(b.getGame() != null){
				games.add(b.getGame());
			}
		}

		return games;
	}

	@Override
	public BlogEntry getBlogEntry(String urlName) {
		return (BlogEntry)sessionFactory.getCurrentSession().createCriteria(BlogEntry.class).add( Restrictions.eq("urlName", urlName)).uniqueResult();
	}

	@Override
	public List<BlogEntry> getBlogsOfGame(String gameName) {
		List<BlogEntry> blogs =(List<BlogEntry>) sessionFactory.getCurrentSession().createCriteria(BlogEntry.class)
				.add(Restrictions.eq("game", gameName))
				.addOrder(Order.desc("timestamp"))
				.list();
		return blogs;
	}

	@Override
	public List<BlogEntry> getBlogsOfMember(String member) {
		List<BlogEntry> blogs =(List<BlogEntry>) sessionFactory.getCurrentSession().createCriteria(BlogEntry.class)
				.add(Restrictions.eq("member", member))
				.addOrder(Order.desc("timestamp"))
				.list();
		return blogs;
	}

	@Override
	public void updateBlogEntry(BlogEntry blog) {
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
	}

	@Override
	public boolean doesBlogEntryExist(String url) {
		BlogEntry blog = (BlogEntry) sessionFactory.getCurrentSession().createCriteria(BlogEntry.class)
				.add( Restrictions.idEq(url) ).uniqueResult();
		
		return blog != null;
	}

	@Override
	public BlogEntry getNewestBlogByMember(String member) {
		return (BlogEntry) sessionFactory.getCurrentSession().createCriteria(BlogEntry.class)
				.add(Restrictions.eq("member", member))
				.addOrder(Order.desc("timestamp"))
				.setMaxResults(1)
				.uniqueResult();
	
	}

}
