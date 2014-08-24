package com.StaticVoidGames.spring.dao;

import java.util.List;
import java.util.Set;

import com.StaticVoidGames.blog.BlogEntry;

public interface BlogEntryDao {
	public void addBlogEntry(BlogEntry blog);
	public void deleteBlogEntry(BlogEntry blog);
	public List<BlogEntry> getAllBlogsByTime();
	public Set<String> getBlogAuthors();
	public Set<String> getBloggedGames();
	public BlogEntry getBlogEntry(String urlName);
	public List<BlogEntry> getBlogsOfGame(String gameName);
	public List<BlogEntry> getBlogsOfMember(String member);
	public void updateBlogEntry(BlogEntry blog);
	public boolean doesBlogEntryExist(String url);
}