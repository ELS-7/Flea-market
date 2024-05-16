package com.xenus.sts.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


public class xenusAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler 
											   implements AuthenticationFailureHandler {
	
    private final Logger logger = LoggerFactory.getLogger(xenusAuthenticationFailureHandler.class);	

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) 
											   throws IOException, ServletException {

		String targetUrl = "/com/login?param=authFail";
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
        
       // if (logger.isInfoEnabled()) 
        	logger.info("로그인 실패-----------------> Redirecting to URL:{}", targetUrl);
	}
}