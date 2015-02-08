package com.StaticVoidGames.spring.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.StaticVoidGames.spring.dao.MemberDao;

/**
 * Configuration class responsible for handling logins.
 * This is probably overkill for what we need to do, but oh well.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * Connection to the database
	 */
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthenticationSuccessHandler loginSuccessHandler;


	/**
	 * Sets up the login handler, including BCrypt
	 */
	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select memberName, bcryptHash, activated from Members where memberName = ?")
		.authoritiesByUsernameQuery(" select memberName,'default' from Members where memberName = ?")
		.passwordEncoder(new BCryptPasswordEncoder(10));
	}
	 
	@Override
	public void configure(WebSecurity web) throws Exception {
		//TODO: do we need this empty function?
	}
	 
	/**
	 * Setup the login and logout URLs
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeUrls()
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated() 
		.and()

		.formLogin()  
		.loginUrl("/login") 
		.successHandler(loginSuccessHandler)
		.failureUrl("/login?error=yep")
		.permitAll()
		.and()
		
		.logout()
		// .deleteCookies("cookie name")
		.invalidateHttpSession(false)
		.logoutUrl("/logout")
		.logoutSuccessHandler(new LogoutSuccessHandler2())
		.logoutSuccessUrl("/logout-success");
	}



	

	
}
