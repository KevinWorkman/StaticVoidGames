package com.StaticVoidGames.spring.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Initializer class taking over for web.xml.
 */
public class WebAppInitializer implements WebApplicationInitializer {


	public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
		container.register(WebConfig.class);

		servletContext.addListener(new ContextLoaderListener(container));

		DispatcherServlet dispatcher = new DispatcherServlet(container);
		dispatcher.setThrowExceptionIfNoHandlerFound(true);

		ServletRegistration.Dynamic springDispatcher = servletContext.addServlet("dispatcher", dispatcher);
		springDispatcher.setLoadOnStartup(1);
		springDispatcher.addMapping("/");
		springDispatcher.setAsyncSupported(true);
	}
}