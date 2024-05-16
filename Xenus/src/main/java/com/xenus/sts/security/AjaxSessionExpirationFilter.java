package com.xenus.sts.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter("/AjaxSessionExpirationFilter")
public class AjaxSessionExpirationFilter implements Filter {

	private int customSessionExpiredErrorCode = 901;
	private final Logger logger = LoggerFactory.getLogger(AjaxSessionExpirationFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Property check here
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		HttpSession currentSession = ((HttpServletRequest) request).getSession(false);
		
		if (currentSession == null) {
			String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
			
			if ("XMLHttpRequest".equals(ajaxHeader)) {
				String requestUri = ((HttpServletRequest) request).getRequestURI();
				logger.info("Ajax call detected sssion disconnected, {}(error code), {}(ip), {}(uri before)", this.customSessionExpiredErrorCode, request.getRemoteAddr(), requestUri);
				
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendError(this.customSessionExpiredErrorCode);
				
			} else {
				//pass the request along the filter chain
				chain.doFilter(request, response);
			}
			
		} else {
			//pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}