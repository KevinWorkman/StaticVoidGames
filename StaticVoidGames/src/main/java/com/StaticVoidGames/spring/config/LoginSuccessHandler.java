package com.StaticVoidGames.spring.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.StaticVoidGames.members.Member;
import com.StaticVoidGames.spring.dao.MemberDao;
import com.StaticVoidGames.spring.util.AttributeNames;
import com.StaticVoidGames.spring.util.HtmlEscaper;

/**
 * Class used by Spring to keep track of the currently logged-in user.
 */
@Service
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private Environment env;
	
	@Autowired
	private MemberDao memberDao;


	/**
	 * This function is called after the user successfully logs in.
	 * Adds an attribute to the session to keep track of the currently logged-in user.
	 * TODO: When the site is update, all sessions are lost, so everybody is logged out. Maybe do something smarter (maybe with cookies?) so this doesn't happen?
	 */
	@Override
	@Transactional
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
					throws ServletException, IOException {

		//sessions never expire
		request.getSession().setMaxInactiveInterval(-1);

		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedInUser = user.getUsername();
		String escapedLoggedInUser = HtmlEscaper.escape(loggedInUser);
		String urlEscapedLoggedInUser = HtmlEscaper.escapeUrlPart(loggedInUser);

		request.getSession().setAttribute(AttributeNames.isLoggedIn, true);
		request.getSession().setAttribute(AttributeNames.loggedInUser, loggedInUser);
		request.getSession().setAttribute(AttributeNames.escapedLoggedInUser, escapedLoggedInUser);
		request.getSession().setAttribute(AttributeNames.urlEscapedLoggedInUser, urlEscapedLoggedInUser);

		//redirect to the last page visited
		//TODO: this acts weird if the user opens multiple tabs or hits the back button before logging in
		String url = "/";
		Object urlObj = request.getSession().getAttribute("loginUrl");
		if(urlObj != null){
			url = (String) request.getSession().getAttribute(AttributeNames.lastPageVisited);
		}

		if(request.getSession().getAttribute("sso") != null){
			
			//https://meta.discourse.org/t/sso-example-for-jsp/22786

			String discourseSsoSecret = env.getProperty("discourse.sso.secret");
			String sso = (String) request.getSession().getAttribute("sso");
			String sig = (String) request.getSession().getAttribute("sig");
			
			//now remove them
			request.getSession().removeAttribute("sso");
			request.getSession().removeAttribute("sig");
		
			try {
				if (checksum(discourseSsoSecret, sso).equals(sig)) {
					
					Member member = memberDao.getMember(loggedInUser);
					
				    String urlDecode = URLDecoder.decode(sso, "UTF-8");
				    String nonce = new String(Base64.decode(urlDecode.getBytes("UTF-8")));
				    String urlEncode = nonce
				            + "&name=" + URLEncoder.encode(member.getMemberName(), "UTF-8")
				            + "&username=" + URLEncoder.encode(member.getMemberName(), "UTF-8")
				            + "&email=" + URLEncoder.encode(member.getEmail(), "UTF-8")
				            + "&external_id=" + URLEncoder.encode(member.getMemberName(), "UTF-8");
				    
				    if(member.getImageUrl() != null && !member.getImageUrl().contains(" ")){
				    	String s3Endpoint = env.getProperty("s3.endpoint");
				    	urlEncode += "&avatar_url=" + URLEncoder.encode(s3Endpoint + "/users/" + member.getMemberName() + "/" + member.getImageUrl(), "UTF-8");
				    	urlEncode += "&avatar_force_update=1";
				    }
				    
				    String urlBase64 = new String(Base64.encode(urlEncode.getBytes("UTF-8")));
				    int length = 0;
				    int maxLength = urlBase64.length();
				    final int STEP = 60;
				    String urlBase64Encode = "";
				    while (length < maxLength) {
				        urlBase64Encode += urlBase64.substring(length, length + STEP < maxLength ? length + STEP : maxLength) + "\n";
				        length += STEP;
				    }
				    response.sendRedirect("http://forum.staticvoidgames.com/session/sso_login?sso=" + URLEncoder.encode(urlBase64Encode, "UTF-8") + "&sig=" + checksum(discourseSsoSecret, urlBase64Encode));
				}
			} 
			catch (InvalidKeyException | NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}




		}
		else{
			this.getRedirectStrategy().sendRedirect(request, response, url);
		}
	}

	private String checksum(String macKey, String macData) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		Mac mac = Mac.getInstance("HmacSHA256");
		byte[] keyBytes = macKey.getBytes("UTF-8");
		byte[] dataBytes = macData.getBytes("UTF-8");
		SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
		mac.init(secretKey);
		byte[] doFinal = mac.doFinal(dataBytes);
		byte[] hexBytes = new Hex().encode(doFinal);
		return new String(hexBytes);
	}

}