package com.xenus.sts.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.xenus.sts.util.Constant;
import com.xenus.sts.util.RequestUtil;
import com.xenus.sts.util.StringEncrypter;
import com.xenus.sts.vo.LoginUserSecurity;

//@Service("integrationAuthenticationService") //bean에 선언
public class IntegrationAuthenticationService {

	private final Logger logger = LoggerFactory.getLogger(IntegrationAuthenticationService.class);	
	
	public LoginUserSecurity getLoginUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		
		if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
			return null;
		}
		
		LoginUserSecurity user = (LoginUserSecurity) auth.getPrincipal();
		return user;
	}
	
	
	public String SetIntergrationID(HttpServletRequest request,
									HttpServletResponse response, String decryptID) throws Exception {
	
		String value=null;
		StringEncrypter xenusEncrypter = new StringEncrypter(Constant.xenus_key, Constant.xenus_iv);
		String encryptedID = xenusEncrypter.encrypt(decryptID);
	
		value = URLEncoder.encode(Constant.xenus_cookie_member,"utf-8") + "=" + URLEncoder.encode(encryptedID,"utf-8");
		RequestUtil.setCookie(response, Constant.xenus_cookie_name, value, Constant.xenus_cookie_path);
		return encryptedID;
	}
	

	public String GetIntergrationID(HttpServletRequest request) throws Exception {
		System.out.println(request.getHeader ("Cookie"));
		
		String valueString = GetIntegrationCookie(request.getHeader ("Cookie"), Constant.xenus_cookie_name);
		
		if ((valueString==null)||(valueString.length()==0)) return null;
		
		String encryptedID =  valueString;
		String decryptedID = null;
		
		if ( (encryptedID!=null)&&(!encryptedID.equals("")) ) {
				StringEncrypter xenusEncrypter = new StringEncrypter(Constant.xenus_key, Constant.xenus_iv); 
			
				decryptedID = xenusEncrypter.decrypt(encryptedID);
			}
			else return null;
			
			return decryptedID;
	}
	
	
	public String GetIntegrationInfo(HttpServletRequest request, String name) throws Exception {
		
		Cookie pCookie = RequestUtil.getCookie(request,  name);
		if (pCookie == null) return null;

		String valueString= pCookie.getValue();
		logger.debug("Step 1 >>>>>>>>>>쿠키쿠키!! Value:{}",valueString);
		if ((valueString==null)||(valueString.length()==0)) return null;
	
		String memberString =URLDecoder.decode(valueString, "utf-8");
		String encryptedID =  memberString;
		String decryptedID = null;
	
		if ( (encryptedID!=null)&&(!encryptedID.equals("")) ) {
				StringEncrypter xenusEncrypter = new StringEncrypter(Constant.xenus_key, Constant.xenus_iv); 
				decryptedID = xenusEncrypter.decrypt(encryptedID);
		}
		else {
			return null;
		}
		
		return decryptedID;
	}

	
	private String GetIntegrationCookie(String strCookies, String strCookieName) 
								throws UnsupportedEncodingException { String[] cookies = strCookies.split(";");
		String strValue=null;
		
		for(int i=0;i < cookies.length; i++) {
			String s=cookies[i].trim();
			
			if(s.startsWith(strCookieName)) {
				String tmp = s.substring(s.indexOf("=")+1, s.length());	
				String[] membersArray=tmp.split("&");
				
				strValue= GetIntegrationCookieMember(membersArray, Constant.xenus_cookie_member);
				break;
			}
		}
		return strValue;
	}
	
	
	private String GetIntegrationCookieMember(String[] strMemberAndValues, String strMemberName) 
								throws UnsupportedEncodingException {
		String strValue=null;
		
		for (int i=0; i<strMemberAndValues.length; i++) {
			
			String[] nAndv=strMemberAndValues[i].split("=");
			
			String strMemName = URLDecoder.decode(nAndv[0], "utf-8");
			String strMemVal  = URLDecoder.decode(nAndv[1], "utf-8");
			if(strMemberName.equals(strMemName)) {
				strValue=strMemVal;
				break;
			}
		}
		return strValue;
	}
}
