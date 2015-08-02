package com.StaticVoidGames.spring.dao.jpa;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StaticVoidGames.blog.BlogEntry;
import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.games.Game;
import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.spring.dao.MemberDao;

@Service
public class MemberJpaDao implements MemberDao{

	private SessionFactory sessionFactory;

	@Autowired
	public MemberJpaDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int getMemberCount() {
		int count = ((Long)sessionFactory.getCurrentSession().createCriteria(Member.class).setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return count;
	}

	@Override
	public List<Member> getAllMembers() {
		List<Member> members =(List<Member>) sessionFactory.getCurrentSession().createCriteria(Member.class).list();
		return members;
	}

	@Override
	public Member getMember(String memberName) {
		Member member = (Member) sessionFactory.getCurrentSession().createCriteria(Member.class).add( Restrictions.idEq(memberName) ).uniqueResult();
		return member;
	}

	@Override
	public void updateMember(Member member) {
		sessionFactory.getCurrentSession().saveOrUpdate(member);
	}

	@Override
	public int getCommentCount(String member){
		Number memberComments = (Number) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.add( Restrictions.eq("commentingMember", member))
				.setProjection(Projections.rowCount())
				.uniqueResult();
		
		return memberComments.intValue();
	}
	
	@Override
	public int getBlogCommentCount(String member) {
		Number memberComments = (Number) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.add( Restrictions.eq("commentingMember", member))
				.add(Restrictions.eq("type", "BlogComment"))
				.setProjection(Projections.rowCount())
				.uniqueResult();
		
		return memberComments.intValue();
	}

	@Override
	public int getAccountCommentCount(String member) {
		Number memberComments = (Number) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.add( Restrictions.eq("commentingMember", member))
				.add(Restrictions.eq("type", "AccountComment"))
				.setProjection(Projections.rowCount())
				.uniqueResult();
		
		return memberComments.intValue();
	}
	
	@Override
	public int getGameCommentCount(String member) {
		Number memberComments = (Number) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.add( Restrictions.eq("commentingMember", member))
				.add(Restrictions.eq("type", "GameComment"))
				.setProjection(Projections.rowCount())
				.uniqueResult();
		
		return memberComments.intValue();
	}
	
	@Override
	public int getGameCount(String member){
		Number memberGames = (Number) sessionFactory.getCurrentSession().createCriteria(Game.class)
				.add( Restrictions.eq("member", member))
				.add(Restrictions.eq("published", true))
				.setProjection(Projections.rowCount())
				.uniqueResult();
		
		return memberGames.intValue();
	}
	
	@Override
	public int getBlogCount(String member){
		Number memberBlogs = (Number) sessionFactory.getCurrentSession().createCriteria(BlogEntry.class)
				.add( Restrictions.eq("member", member))
				.setProjection(Projections.rowCount())
				.uniqueResult();
		
		return memberBlogs.intValue();
	}
	
	/**
	 * Experimental.
	 */
	@Override
	public int getPoints(String member) {
		
		int memberComments = getCommentCount(member);
		int memberGames = getGameCount(member);
		int memberBlogs = getBlogCount(member);
		
		return memberComments*10 + memberGames*100 + memberBlogs*20;
	}
}
