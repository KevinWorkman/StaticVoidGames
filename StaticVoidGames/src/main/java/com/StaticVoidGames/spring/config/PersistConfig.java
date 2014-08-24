package com.StaticVoidGames.spring.config;

import java.beans.PropertyVetoException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;



/**
 * Configuration class responsible for connecting to the database and setting up Hibernate.
 */
@Configuration
@ComponentScan({"com.StaticVoidGames.spring.dao.jpa"})
@EnableTransactionManagement
public class PersistConfig {

	/**
	 * Environment gives you access to the properties file.
	 */
	@Autowired
	private Environment env;
	
	/**
	 * Connection to the live database. Keep this around so you can close it upon shutdown.
	 * The easiest way to do this would be to annotate the dataSource() method with Bean(destroyMethod = "close"), but that doesn't work with Derby.
	 */
	private BoneCPDataSource ds;

	@Bean
	public HibernateExceptionTranslator exceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource, JpaVendorAdapter vendorAdapter) {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource);
		localSessionFactoryBean.setPackagesToScan("com.StaticVoidGames.members", "com.StaticVoidGames.games", "com.StaticVoidGames.comments", "com.StaticVoidGames.blog", "com.StaticVoidGames.notifications");

		Properties properties = new Properties();
		properties.putAll(vendorAdapter.getJpaPropertyMap());
		localSessionFactoryBean.setHibernateProperties(properties);
		
		return localSessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
		return hibernateTransactionManager;
	}


	/**
	 * Sets up the connection to the database.
	 * TODO: This method connects to either the local Derby database or the real mysql database based on the database location property. This is almost definitely not the best way to do this. What's the Spring way to do it?
	 */
	@Bean
	public DataSource dataSource() throws SQLException, PropertyVetoException {

		String databaseLocation = env.getProperty("database.location");
		String databaseUsername = env.getProperty("database.username");
		String databasePassword = env.getProperty("database.password");

		DataSource dataSource = null;

		if(databaseLocation.startsWith("jdbc:derby")){
			dataSource = new SimpleDriverDataSource(new org.apache.derby.jdbc.EmbeddedDriver(), databaseLocation, databaseUsername, databasePassword);
			
		}
		else if(databaseLocation.startsWith("jdbc:mysql")){
			
			/*
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
	        cpds.setJdbcUrl(databaseLocation);
	        cpds.setUser(databaseUsername);
	        cpds.setPassword(databasePassword);
	        
	        
	        cpds.setMinPoolSize(5);
	        cpds.setMaxPoolSize(10);
	        cpds.setCheckoutTimeout(1800);
	        cpds.setIdleConnectionTestPeriod(75);
	        cpds.setMaxStatements(50);
	        
	        dataSource = cpds;
	        */
			
			
			//BoneCP handles the connection pool
			ds = new BoneCPDataSource();
	        ds.setDriverClass("com.mysql.jdbc.Driver");
	        ds.setJdbcUrl(databaseLocation);
	        ds.setUser(databaseUsername);
	        ds.setPassword(databasePassword);
	        ds.setPartitionCount(1);
	        //the mysql connection limit appears to be 25. The connection limit for the website is low so I can test locally without blowing everything up, but this could be increased if we have any problems. Although we should probably just upgrade to Amazon RDS anyway.
	        ds.setMaxConnectionsPerPartition(10);

	        //the connections time out (the dreaded "expected 4 bytes but got 0" error), so this sends a little query through every so often to keep the connection alive
	        //TODO: figure out the connection timeout period?
	        ds.setIdleConnectionTestPeriodInSeconds(45);
	       
	        
	        
	        dataSource = ds;
		
			//dataSource = new SimpleDriverDataSource(new  com.mysql.jdbc.Driver(), databaseLocation, databaseUsername, databasePassword);
		}

		return dataSource;
	}


	/**
	 * Sets up Hibernate, which creates plain old Java Objects from our database rows
	 */
	@Bean
	JpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		String databaseLocation = env.getProperty("database.location");

		//TODO: switching on the database location property is almost certainly not the correct thing to do
		if(databaseLocation.startsWith("jdbc:derby")){
			vendorAdapter.setDatabase(Database.DERBY);
			vendorAdapter.setDatabasePlatform("org.hibernate.dialect.DerbyDialect");
		}
		else if(databaseLocation.startsWith("jdbc:mysql")){
			vendorAdapter.setDatabase(Database.MYSQL);
			vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
		}
		
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(false);

		vendorAdapter.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "update");
		vendorAdapter.getJpaPropertyMap().put("hbm2ddl.auto", "update");

		return vendorAdapter;
	}


	/**
	 * Closes the connection to the database.
	 */
	@PreDestroy
	public void shutdown() {


		String databaseLocation = env.getProperty("database.location");
		
		if(databaseLocation.startsWith("jdbc:derby")){

			final String SHUTDOWN_CODE = "XJ015";

			try {
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			}
			catch (SQLException e) {
				// Derby 10.9.1.0 shutdown raises a SQLException with code "XJ015"
				if (!SHUTDOWN_CODE.equals(e.getSQLState())) {
					e.printStackTrace();
				}
			}
		}
		else if(databaseLocation.startsWith("jdbc:mysql")){
			ds.close();
		}
	}
}