package com.StaticVoidGames.spring.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.StaticVoidGames.games.Game;
import com.StaticVoidGames.games.GameExecutable;
import com.StaticVoidGames.spring.dao.GameDao;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class GameJpaDao implements GameDao{
	
	@Autowired
	private Environment env;
	
	private SessionFactory sessionFactory;

	@Autowired
	public GameJpaDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Game> getPublishedGamesOfMember(String member) {
		List<Game> games = (List<Game>) sessionFactory.getCurrentSession().createCriteria(Game.class)
				.add( Restrictions.eq("member", member))
				.add( Restrictions.eq("published", true))
				.addOrder(Order.desc("timestamp"))
				.list();

		return games;
	}
	
	@Override
	public List<Game> getUnpublishedGamesOfMember(String member){

		List<Game> games = (List<Game>) sessionFactory.getCurrentSession().createCriteria(Game.class)
				.add( Restrictions.eq("member", member))
				.add( Restrictions.eq("published", false))
				.addOrder(Order.desc("timestamp"))
				.list();

		return games;
	}

	@Override
	public Game getGame(String game) {
		return (Game) sessionFactory.getCurrentSession().createCriteria(Game.class)
				.add( Restrictions.idEq(game) ).uniqueResult();
	}

	@Override
	public void updateGame(Game gameObj) {
		sessionFactory.getCurrentSession().saveOrUpdate(gameObj);
	}

	@Override
	public List<Game> getAllPublishedGames() {
		List<Game> games = (List<Game>) sessionFactory.getCurrentSession().createCriteria(Game.class)
				.add( Restrictions.eq("published", true))
				.addOrder(Order.desc("timestamp"))
				.list();

		return games;
	}

	@Override
	public List<GameExecutable> getGameExecutables(String game) {
		List<GameExecutable> gameExecutabless = (List<GameExecutable>) sessionFactory.getCurrentSession().createCriteria(GameExecutable.class)
				.add( Restrictions.eq("game", game))
				.list();

		return gameExecutabless;
	}
	
	
	@Override
	public List<String> getGameLibFiles(String game){

		List<String> libFiles = new ArrayList<String>(); 

		try{
			
			String awsAccessKey = env.getProperty("aws.accessKey");
			String awsSecretKey = env.getProperty("aws.secretKey");
			String bucket = env.getProperty("s3.bucket");
			
			AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
			
			String prefix = "games/" + game + "/lib/";

			ObjectListing ol =s3.listObjects(bucket, prefix); 
			
			for(S3ObjectSummary os : ol.getObjectSummaries()){
				if(os.getSize() > 0){
					libFiles.add("http://" + bucket + "/" + os.getKey());
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return libFiles;

	}
}
