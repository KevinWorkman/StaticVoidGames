package com.StaticVoidGames.spring.util;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.StaticVoidGames.members.Member;


/**
 * Utility class that provides a way to programatically send an activation email to newly registered users.<br/>
 * TODO: This class doesn't seem very "Spring-like" <br/>
 * TODO: I could see using this functionality for things like notifications. If we do, split this into multiple classes: one for sending email, others for using it
 */
public class ActivationEmailSender {

	//this class needs access to the properties
	//this doesn't seem very Spring-like
	private Environment env;

	public ActivationEmailSender(Environment env){
		this.env = env;
	}

	public static String generateRandomKey(int length){
		String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		while(sb.length() < length){
			sb.append(alphabet.charAt((int) (Math.random()*alphabet.length())));
		}
		return sb.toString();
	}
	
	private JavaMailSenderImpl getJavaMailSender(){
		JavaMailSenderImpl jms = new JavaMailSenderImpl();
		
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", env.getProperty("activation.mail.smtp.starttls.enable"));
		properties.put("mail.smtp.host", env.getProperty("activation.mail.smtp.host"));
		properties.put("mail.smtp.user", env.getProperty("activation.mail.smtp.user"));
		properties.put("mail.smtp.password", env.getProperty("activation.mail.smtp.password"));
		properties.put("mail.smtp.port", env.getProperty("activation.mail.smtp.port"));
		properties.put("mail.smtp.auth", env.getProperty("activation.mail.smtp.auth"));

		jms.setJavaMailProperties(properties);
		jms.setHost(properties.getProperty("mail.smtp.host"));
		jms.setUsername(properties.getProperty("mail.smtp.user"));
		jms.setPassword(properties.getProperty("mail.smtp.password"));
		
		return jms;
	}

	public boolean sendActivationEmail(Member member){

		try{
			
			JavaMailSenderImpl jms = getJavaMailSender();


			String address = "http://StaticVoidGames.com/members/" + member.getMember() + "/activate/" + member.getActivationId();

			StringBuilder sb = new StringBuilder();
			sb.append("<p>Hello, ");
			sb.append(member.getMemberName());
			sb.append("!</p>");
			sb.append("<p>Please click the link below to activate your account on Static Void Games:</p>");
			sb.append("<p><a href='" + address + "' >" + address + "</a></p>");

			sb.append("<p>Please do not reply to this email, as this account is a robot.</p>");
			sb.append("<p>If you have a question, don't hesitate to <a href='http://staticvoidgames.com/about/contact'>contact me</a>!</p>");

			MimeMessage mimeMessage = jms.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
			mimeMessage.setContent(sb.toString(), "text/html");
			helper.setTo(member.getEmail());
			helper.setSubject("Static Void Games Account Activation");
			helper.setFrom(env.getProperty("activation.mail.smtp.user"), env.getProperty("activation.mail.smtp.user"));
			jms.send(mimeMessage);
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendPasswordResetEmail(Member member){


		try{
			
			JavaMailSenderImpl jms = getJavaMailSender();


			String address = "http://StaticVoidGames.com/members/" + member.getMember() + "/resetPassword/" + member.getActivationId();

			StringBuilder sb = new StringBuilder();
			sb.append("<p>Hello, ");
			sb.append(member.getMemberName());
			sb.append("!</p>");
			
			sb.append("<p>Please click the link below to reset your password on Static Void Games:</p>");
			sb.append("<p><a href='" + address + "' >" + address + "</a></p>");
			sb.append("<p>Please do not reply to this email, as this account is a robot.</p>");
			sb.append("<p>If you did not intend to reset your password, you can ignore this email.</p>");
			sb.append("<p>If you have a question, don't hesitate to <a href='http://staticvoidgames.com/about/contact'>contact me</a>!</p>");

			MimeMessage mimeMessage = jms.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
			mimeMessage.setContent(sb.toString(), "text/html");
			helper.setTo(member.getEmail());
			helper.setSubject("Static Void Games Password Reset");
			helper.setFrom(env.getProperty("activation.mail.smtp.user"), env.getProperty("activation.mail.smtp.user"));
			jms.send(mimeMessage);
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	
	}
}
