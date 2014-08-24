package com.StaticVoidGames.spring.dao;

import java.util.List;

import com.StaticVoidGames.comments.Comment;

public interface CommentDao {
	public List<Comment> getComments(String thingCommentedOn, String type);
	public void addComment(Comment comment);
	public Comment getComment(long id);
	public void deleteComment(Comment comment);
	public long getNextId();
}
