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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This whole thing needs to be overhauled, as it is currently WAY too slow.
 */
@Service
public class NotificationsJpaDao implements NotificationsDao{
	
	private SessionFactory sessionFactory;

	@Autowired
	public NotificationsJpaDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int getUnviewedCommentsCountForSubscription(Subscription s) {
		Number n = (Number) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.add(Restrictions.eq("thingCommentedOn", s.getEntityId()))
				.add(Restrictions.ne("commentingMember", s.getMember()))
				.add(Restrictions.gt("timestamp", s.getLastNotificationViewedTimestamp()))
				.setProjection(Projections.rowCount())
				.uniqueResult();
		return n.intValue();
	}
	
	@Override
	public int getUnviewedCommentsCountNoSession(String user) {
		Session session = sessionFactory.openSession();
		
		int count = 0;
		
		for(Subscription s : getSubscriptionsForMember(user, session)){
			count += getUnviewedCommentsCountForSubscription(s, session);
		}
		
		session.close();
		
		return count;
	}

	private int getUnviewedCommentsCountForSubscription(Subscription s, Session session) {
		Number n = (Number) session.createCriteria(Comment.class)
				.add(Restrictions.eq("thingCommentedOn", s.getEntityId()))
				.add(Restrictions.ne("commentingMember", s.getMember()))
				.add(Restrictions.gt("timestamp", s.getLastNotificationViewedTimestamp()))
				.setProjection(Projections.rowCount())
				.uniqueResult();
		return n.intValue();
	}

	@Override
	public int getUnviewedCommentsCount(String user) {
		
		int count = 0;
		
		for(Subscription s : getSubscriptionsForMember(user)){
			count += getUnviewedCommentsCountForSubscription(s);
		}
	
		return count;
	}

    public List<Comment> getCommentsForSubscriptions(Iterable<Subscription> subscriptions) {
        QComment comment = QComment.comment1;

        BooleanBuilder predicate = new BooleanBuilder();

        // loop through each subscription
        for (Subscription subscription : subscriptions) {
            // create the the comment specific predicate
            predicate.or(
                comment.thingCommentedOn.eq(subscription.getEntityId())
                .and(comment.commentingMember.eq(subscription.getMember()))
            );
        }

        return createQuery().from(comment).where(predicate).orderBy(comment.timestamp.desc()).list(comment);
    }

    private HibernateQuery createQuery() {
        return new HibernateQuery(sessionFactory.getCurrentSession());
    }


    @Override
	public List<Comment> getUnviewedCommentsForSubscription(Subscription s) {
		List<Comment> notifications = (List<Comment>) sessionFactory.getCurrentSession().createCriteria(Comment.class)
				.add(Restrictions.eq("thingCommentedOn", s.getEntityId()))
				.add(Restrictions.ne("commentingMember", s.getMember()))
				.add(Restrictions.gt("timestamp", s.getLastNotificationViewedTimestamp())).list();
		return notifications;
	}

	private List<Subscription> getSubscriptionsForMember(String member, Session session) {
		
		List<Subscription> subscriptions = (List<Subscription>) session.createCriteria(Subscription.class)
				.add( Restrictions.eq("member", member))
				.list();
		
		return subscriptions;
	}

	@Override
	public List<Subscription> getSubscriptionsForMember(String member) {
        QSubscription subscription = QSubscription.subscription;

        Predicate predicate = subscription.member.eq(member);

		return createQuery().from(subscription).where(predicate).list(subscription);
	}

	
	@Override
	public List<Comment> getUnviewedCommentsForMember(String member) {
		
		List<Comment> notifications = new ArrayList<Comment>();
		
		for(Subscription s : getSubscriptionsForMember(member)){
			notifications.addAll(getUnviewedCommentsForSubscription(s));
		}
		
		Collections.sort(notifications, new Comparator<Comment>(){
			@Override
			public int compare(Comment n1, Comment n2) {
				return Long.valueOf(n2.getTimestamp()).compareTo(n1.getTimestamp());
			}
			
		});
		
		return notifications;
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
		List<Subscription> subscriptions = (List<Subscription>) sessionFactory.getCurrentSession().createCriteria(Subscription.class)
				.add( Restrictions.eq("member", member))
				.list();
		
		for(Subscription s : subscriptions){
			s.setLastNotificationViewedTimestamp(time);
			sessionFactory.getCurrentSession().saveOrUpdate(s);
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
