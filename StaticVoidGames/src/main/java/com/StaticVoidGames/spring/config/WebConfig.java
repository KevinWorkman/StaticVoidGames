package com.StaticVoidGames.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.StaticVoidGames.spring.controller.Interceptor;

/**
 * Main configuration class that takes the place of web.xml
 */
@Configuration
@EnableWebMvc 
@EnableWebSecurity
@Import({PersistConfig.class, SecurityConfig.class}) 
@ComponentScan({"com.StaticVoidGames.spring.controller", "com.StaticVoidGames.spring.config"})
public class WebConfig extends WebMvcConfigurerAdapter {


	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	/**
	 * Setup the static resources
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
		
		//this gives us access to images inside the tutorialsContent directory
		registry.addResourceHandler("/tutorialsContent/**").addResourceLocations("/WEB-INF/tutorialsContent/");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		super.configureDefaultServletHandling(configurer);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(interceptor()); 
	}

	/**
	 * The Interceptor is called before and/or after every page load to set attributes we need everywhere
	 * TODO: Do we need both this function and the addInterceptors() method?
	 */
	@Bean
	public Interceptor interceptor() {
		return new Interceptor();
	}

	/**
	 * This allows us to handle file uploads
	 */
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}
}

