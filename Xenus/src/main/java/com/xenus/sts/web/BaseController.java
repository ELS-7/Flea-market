package com.xenus.sts.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.xenus.sts.security.IntegrationAuthenticationService;
import com.xenus.sts.util.AesCrypto;
import com.xenus.sts.util.Constant;
import com.xenus.sts.vo.LoginUserSecurity;

//The base Spring MVC controller. Used to provide common functionality to all controllers.

public class BaseController {
	
	@Autowired
	SessionRegistry sessionRegistry;
	
	@Autowired
	private IntegrationAuthenticationService pInterAuthService;
	
	@SuppressWarnings("unused")
	@Autowired
	private AuthenticationManager authenticationManager;
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	

	public String aesEncrypt(String value) {
		if (value == null || "".equals(value)) return value;
		
		try {
			String out = AesCrypto.encrypt(value, Constant.xenus_key);
			return out;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public String aesDecrypt(String value) {
		if (value == null || "".equals(value)) return value;
		
		try {
			String out = AesCrypto.decrypt(value, Constant.xenus_key);
			return out;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	
	public int getNumberOfUsers() {
		return sessionRegistry.getAllPrincipals().size();
	}
	
	public String getDomainName(HttpServletRequest request){
		return request.getServerName();
	}
	
	public LoginUserSecurity getLoginUser() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if (a.getPrincipal().equals("anonymousUser")) {
			return null;
		}
		
		LoginUserSecurity user = (LoginUserSecurity)a.getPrincipal();
		return user;
	}
	
	public LoginUserSecurity getIntegrationAuthLoginUser(HttpServletRequest request) throws Exception {
		String user_id  = pInterAuthService.GetIntegrationInfo(request, Constant.xenus_cookie_user_id);	
		String user_idx = pInterAuthService.GetIntegrationInfo(request, Constant.xenus_cookie_user_idx);

		LoginUserSecurity user = null;
		
		if ((user_id !=null) && (user_idx !=null)) {
			if (logger.isInfoEnabled())
				logger.info("LoginUser id: {} idx:{} ", user_id, user_idx);
		}
		return user;
	}
	
	public boolean setIntegrationAuthLoginUser(HttpServletRequest request, HttpServletResponse response, LoginUserSecurity bean){
		
		// if (bean.getAccessKey() !=  null) {
		//	UsernamePasswordAuthenticationToken token = new IntegrationAuthenticationToken(bean.getAccessKey(), bean.getAccessKey(),  bean.getAccessKey());
		
		//	Authentication authentication = authenticationManager.authenticate(token);
		//	SecurityContextHolder.getContext().setAuthentication(authentication);
		//	persistAuthentication(authentication, request.getSession());
	
			//handler.onAuthenticationSuccess(request, response, authentication);
		//	return true;
		// }
		return false;
	}
	
	@SuppressWarnings("unused")
	private void persistAuthentication(Authentication authentication, HttpSession session) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute(
			HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
	}
}

//End Of This


