package com.StaticVoidGames.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * This class gives access to the properties defined in the properties file
 */
@Configuration
@PropertySource("classpath:/local.properties")
public class PropertiesConfig {

	@Value( "${database.location}" )
	private String databaseLocation;

	@Value( "${database.username}" )
	private String databaseUsername;

	@Value( "${database.password}" )
	private String databasePassword;

	@Value("${s3.bucket}")
	private String s3Bucket;

	@Value("${s3.endpoint}")
	private String s3Endpoint;

	@Value("${aws.secretKey}")
	private String awsSecretKey;

	@Value("${aws.accessKey}")
	private String awsAccessKey;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
