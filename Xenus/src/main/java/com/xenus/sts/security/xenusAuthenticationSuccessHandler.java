package com.xenus.sts.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.xenus.sts.vo.LoginUserSecurity;


public class xenusAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler 
											   implements AuthenticationSuccessHandler {

	@Autowired
	private IntegrationAuthenticationService pService;
    
	private RequestCache requestCache = new HttpSessionRequestCache();
    private final Logger logger = LoggerFactory.getLogger(xenusAuthenticationSuccessHandler.class);	
	
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
												throws IOException, ServletException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);
		LoginUserSecurity user = pService.getLoginUser();
        
		if (user!=null) {
        	try {
				pService.SetIntergrationID(request, response, user.getEmail());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
		
		//login only --TSLee
        //if (savedRequest == null) {
        //    super.onAuthenticationSuccess(request, response, authentication);
        //    return;
        //}
        
		//no use default target URL --TSLee
        //if (isAlwaysUseDefaultTargetUrl() || StringUtils.hasText(request.getParameter(getTargetUrlParameter()))) {
        //    requestCache.removeRequest(request, response);
        //    super.onAuthenticationSuccess(request, response, authentication);
        //    return;
        //}
        
        clearAuthenticationAttributes(request);

        //redirect
        String targetUrl = "";
        
		if (savedRequest != null)
			targetUrl = "/com/login?param=authSuccess";	
		else 
			targetUrl = "/com/login?param=authSuccess";

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
        
       // if (logger.isInfoEnabled()) 
        	logger.info("로그인 -----------------> {} Redirecting to URL:{}", user.getName(), targetUrl);
	}
}
