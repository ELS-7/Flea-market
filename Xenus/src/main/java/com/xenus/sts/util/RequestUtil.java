package com.xenus.sts.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Convenience class for setting and retrieving cookies.
 */
public final class RequestUtil {
    private static final Log log = LogFactory.getLog(RequestUtil.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private RequestUtil() {
    }

    /**
     * Convenience method to set a cookie
     *
     * @param response the current response
     * @param name the name of the cookie
     * @param value the value of the cookie
     * @param path the path to set it on
     */
    public static void setCookie(HttpServletResponse response, String name,
                                 String value, String path) {
        if (log.isDebugEnabled()) {
            log.debug("Setting cookie '" + name + "' on path '" + path + "'");
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        cookie.setMaxAge(Constant.xenus_cookie_expires); // 30 days
        cookie.setDomain(Constant.xenus_cookie_domain);
      
        response.addCookie(cookie);
    }

    /**
     * Convenience method to get a cookie by name
     *
     * @param request the current request
     * @param name the name of the cookie to find
     *
     * @return the cookie (if found), null if not found
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

        if (cookies == null) {
            return returnCookie;
        }

        for (final Cookie thisCookie : cookies) {
            if (thisCookie.getName().equals(name) && !"".equals(thisCookie.getValue())) {
                returnCookie = thisCookie;
                break;
            }
        }

        return returnCookie;
    }

    /**
     * Convenience method for deleting a cookie by name
     *
     * @param response the current web response
     * @param cookie the cookie to delete
     * @param path the path on which the cookie was set (i.e. /appfuse)
     */
    public static void deleteCookie(HttpServletResponse response,
                                    Cookie cookie, String path) {
        if (cookie != null) {
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }

    /**
     * Convenience method to get the application's URL based on request
     * variables.
     * 
     * @param request the current request
     * @return URL to application
     */
    public static String getAppURL(HttpServletRequest request) {
        if (request == null) return "";
        
        StringBuffer url = new StringBuffer();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }
    
    public static HttpServletRequest getCurrentRequest() {       
    	ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	HttpServletRequest hsr = sra.getRequest();
    	return hsr;   
    }   
    
    public static String getSession(HttpServletRequest request, String Name)
    {
    	String returnValue =null;
    	HttpSession returnSession = request.getSession(false);
    	
    	returnSession.getAttribute(Name);
    	
    	if (returnSession.getAttribute(Name) == null)
    	{
    		return returnValue;
    	}
    	
    	returnValue=(String)returnSession.getAttribute(Name);
    	
    	return returnValue;
    }
    
    public static void setLogin(HttpServletRequest request, HttpServletResponse response, String enCryptID) {
/*    	
    	HttpSession session = request.getSession(true);
    	session.setAttribute(Constant.S_USER, userModel.getUserID());
    	session.setMaxInactiveInterval(Constant.SESSION_TIMEOUT);
    	setCookie(response, "UID", userModel.getUserID(),"/");
    	setCookie(response, "Name", userModel.getName(),"/");
    	setCookie(response, "Role", userModel.getRole(),"/");
*/   
    }
    public static void setLogout(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession(false);
 
    	if (session != null)
    	{
/*
        	session.removeAttribute(Constant.S_USER);
        	session.removeAttribute(Constant.S_ROLE);
        	session.removeAttribute(Constant.S_NAME);
 */       	
        	session.invalidate();
    	}
     
    }
    
    public static void setPath(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession(false);
 
    	if (session != null)
    	{
    		session.setAttribute(Constant.xenus_PATH, request.getRequestURI());
        	
    	}
     
    }
    public static String getPath(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession(false);
 
    	if (session != null)
    	{
    		return (String)session.getAttribute(Constant.xenus_PATH);
        	
    	}
		return null;
     
    }
}
