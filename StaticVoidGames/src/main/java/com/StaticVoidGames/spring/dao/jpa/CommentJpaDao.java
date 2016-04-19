package com.StaticVoidGames.spring.dao.jpa;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.spring.dao.CommentDao;

@Service
public class CommentJpaDao implements CommentDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Comment> getComments(String thingCommentedOn, String type) {
		return (List<Comment>) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.add( Restrictions.eq("thingCommentedOn", thingCommentedOn))
				.add( Restrictions.eq("type", type))
				.addOrder(Order.asc("timestamp"))
				.list();
	}

	@Override
	public void addComment(Comment comment) {
		sessionFactory.getCurrentSession().save(comment);
	}

	@Override
	public Comment getComment(long id) {
		return (Comment)sessionFactory.getCurrentSession().createCriteria(Comment.class).add( Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	public void deleteComment(Comment comment) {
		sessionFactory.getCurrentSession().delete(comment);
	}

	@Override
	public long getNextId() {
		Comment lastComment = (Comment) sessionFactory.getCurrentSession().createCriteria(Comment.class).addOrder(Order.desc("id")).setMaxResults(1).uniqueResult();
		return lastComment.getId() + 1;
	}

	@Override
	public List<Comment> getAllComments() {
		return (List<Comment>)sessionFactory.getCurrentSession().createCriteria(Comment.class).list();
	}

}
