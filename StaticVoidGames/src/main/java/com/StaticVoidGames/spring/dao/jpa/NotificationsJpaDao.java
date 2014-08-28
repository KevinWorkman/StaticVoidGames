package com.StaticVoidGames.spring.dao.jpa;

import com.StaticVoidGames.TimestampedEvent;
import com.StaticVoidGames.blog.BlogEntry;
import com.StaticVoidGames.comments.Comment;
import com.StaticVoidGames.comments.QComment;
import com.StaticVoidGames.games.Game;
import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.notifications.QSubscription;
import com.StaticVoidGames.notifications.Subscription;
import com.StaticVoidGames.spring.dao.NotificationsDao;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.types.Predicate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Provides access to Objects related to notifications.
 */
@Service
public class NotificationsJpaDao implements NotificationsDao{
	
	private SessionFactory sessionFactory;

	@Autowired
	public NotificationsJpaDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int getUnviewedCommentsCount(String user) {
		
		
		List<Subscription> subscriptions = getSubscriptionsForMember(user);
		
		 QComment comment = QComment.comment1;

	        BooleanBuilder predicate = new BooleanBuilder();

	        // loop through each subscription
	        for (Subscription subscription : subscriptions) {
	            // create the the comment specific predicate
	            predicate.or(
	                comment.thingCommentedOn.eq(subscription.getEntityId())
	                .and(comment.commentingMember.ne(subscription.getMember()))
	                .and(comment.timestamp.gt(subscription.getLastNotificationViewedTimestamp()))
	            );
	        }

	        return createQuery().from(comment).where(predicate).list(comment).size();
	}

    public List<Comment> getCommentsForSubscriptions(Iterable<Subscription> subscriptions) {
        QComment comment = QComment.comment1;

        BooleanBuilder predicate = new BooleanBuilder();

        // loop through each subscription
        for (Subscription subscription : subscriptions) {
            // create the the comment specific predicate
            predicate.or(
                comment.thingCommentedOn.eq(subscription.getEntityId())
                .and(comment.commentingMember.ne(subscription.getMember()))
            );
        }

        return createQuery().from(comment).where(predicate).orderBy(comment.timestamp.desc()).list(comment);
    }

    private HibernateQuery createQuery() {
        return new HibernateQuery(sessionFactory.getCurrentSession());
    }

	@Override
	public List<Subscription> getSubscriptionsForMember(String member) {
        QSubscription subscription = QSubscription.subscription;

        Predicate predicate = subscription.member.eq(member);

		return createQuery().from(subscription).where(predicate).list(subscription);
	}

	
	@Override
	public List<Comment> getUnviewedCommentsForMember(String member) {
		
		List<Subscription> subscriptions = getSubscriptionsForMember(member);
		
	       QComment comment = QComment.comment1;

	        BooleanBuilder predicate = new BooleanBuilder();

	        // loop through each subscription
	        for (Subscription subscription : subscriptions) {
	            // create the the comment specific predicate
	            predicate.or(
	                comment.thingCommentedOn.eq(subscription.getEntityId())
	                .and(comment.commentingMember.ne(subscription.getMember()))
	                .and(comment.timestamp.gt(subscription.getLastNotificationViewedTimestamp()))
	            );
	        }

	        return createQuery().from(comment).where(predicate).orderBy(comment.timestamp.desc()).list(comment);
	  
	}	
	

	@Override
	public List<Comment> getCommentsForMember(String member) {
        List<Subscription> subscriptions = getSubscriptionsForMember(member);

        return getCommentsForSubscriptions(subscriptions);
	}

	
	@Override
	public Collection<TimestampedEvent> getEvents(int count) {
		
		//TODO only PUBLISHED events??
		
		//TODO probably a better, more Hibernate-esque way to do this
		TreeSet<TimestampedEvent> allEvents = new TreeSet<TimestampedEvent>(new Comparator<TimestampedEvent>(){
			@Override
			public int compare(TimestampedEvent o1, TimestampedEvent o2) {
				return Long.valueOf(o2.getTimestamp()).compareTo(o1.getTimestamp());
			}
		});
		
		List<Comment> comments = (List<Comment>) sessionFactory.getCurrentSession().createCriteria(Comment.class).list();
		List<Game> games = (List<Game>) sessionFactory.getCurrentSession().createCriteria(Game.class).list();
		List<BlogEntry> blogs = (List<BlogEntry>) sessionFactory.getCurrentSession().createCriteria(BlogEntry.class).list();
		List<Member> members = (List<Member>) sessionFactory.getCurrentSession().createCriteria(Member.class).list();
		
		allEvents.addAll(comments);
		allEvents.addAll(games);
		allEvents.addAll(blogs);
		allEvents.addAll(members);
		
		if(count > allEvents.size()){
			return allEvents;
		}
		else{
			List<TimestampedEvent> events = new ArrayList<TimestampedEvent>();
			for(TimestampedEvent event : allEvents){
				events.add(event);
				
				if(events.size() == count){
					break;
				}
			}
			
			return events;
		}
		
	}	
	

	@Override
	public Collection<TimestampedEvent> getAllEvents() {
		
		//TODO only PUBLISHED events??
		
		//TODO probably a better, more Hibernate-esque way to do this
		//TODO this won't work in the way off chance that two things happen at the exact same time
		TreeSet<TimestampedEvent> events = new TreeSet<TimestampedEvent>(new Comparator<TimestampedEvent>(){

			@Override
			public int compare(TimestampedEvent o1, TimestampedEvent o2) {
				return Long.valueOf(o2.getTimestamp()).compareTo(o1.getTimestamp());
			}
			
		});
		
		List<Comment> comments = (List<Comment>) sessionFactory.getCurrentSession().createCriteria(Comment.class).list();
		List<Game> games = (List<Game>) sessionFactory.getCurrentSession().createCriteria(Game.class).list();
		List<BlogEntry> blogs = (List<BlogEntry>) sessionFactory.getCurrentSession().createCriteria(BlogEntry.class).list();
		List<Member> members = (List<Member>) sessionFactory.getCurrentSession().createCriteria(Member.class).list();
		
		events.addAll(comments);
		events.addAll(games);
		events.addAll(blogs);
		events.addAll(members);
		
		return events;
	}


	@Override
	public void setMemberViewedNotificationsTime(String member, long time) {
//		List<Subscription> subscriptions = (List<Subscription>) sessionFactory.getCurrentSession().createCriteria(Subscription.class)
//				.add( Restrictions.eq("member", member))
//				.list();
//		
//		for(Subscription s : subscriptions){
//			s.setLastNotificationViewedTimestamp(time);
//			sessionFactory.getCurrentSession().saveOrUpdate(s);
//		}
//		
		//above approach is way too slow
		//use HQL instead
		
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("update Subscription S set S.lastNotificationViewedTimestamp = :currentTime where S.member = :memberName");
			query.setLong("currentTime", time);
			query.setString("memberName", member);
			int result = query.executeUpdate();
		}
		catch(Exception e){
			System.out.println("ERROR: " + e.toString());
		}
		
		
	}	
	
	public long getNextId() {
		Subscription lastSubscription = (Subscription) sessionFactory.getCurrentSession().createCriteria(Subscription.class).addOrder(Order.desc("id")).setMaxResults(1).uniqueResult();
		return lastSubscription.getId() + 1;
	}
	
	public Subscription getSubscription(String member, String entityId){
		Subscription subscription = (Subscription) sessionFactory.getCurrentSession().createCriteria(Subscription.class)
				.add( Restrictions.eq("member", member))
				.add(Restrictions.eq("entityId", entityId))
				.uniqueResult();
		
		return subscription;
	}
	
	@Override
	public void updateMemberSubscription(String member, String entityId, String type, String label){
		
		Subscription sub = getSubscription(member, entityId);
		if(sub == null){
			sub = new Subscription();
			sub.setEntityId(entityId);
			sub.setMember(member);
			sub.setId(getNextId());
		}
		
		sub.setLabel(label);
		sub.setLastNotificationViewedTimestamp(System.currentTimeMillis());
		sub.setType(type);
		
		sessionFactory.getCurrentSession().saveOrUpdate(sub);
	}
}
