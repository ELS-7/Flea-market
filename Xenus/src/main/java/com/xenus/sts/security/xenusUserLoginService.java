package com.xenus.sts.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xenus.sts.dao.StsDao;
import com.xenus.sts.vo.CommMap;
import com.xenus.sts.vo.LoginUserSecurity;

public class xenusUserLoginService implements  UserDetailsService  {
	
	@Autowired
	private	StsDao	stsDao;
	private static final Logger logger = LoggerFactory.getLogger(xenusUserLoginService.class);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		return new LoginUserSecurity(username, username, false, false, false, false, AuthorityUtils.createAuthorityList("ROLE_MEMBER"));
	}
	
	public UserDetails loadUserByUsername(String username, String password, String accessKey) throws Exception {
		CommMap 		  param 	  = new CommMap();
		CommMap			  loginUser   = new CommMap();
		LoginUserSecurity loginResult = null;
		
		param.put("username",	username);
		param.put("password",	password);
		
		if (logger.isDebugEnabled())
			logger.debug("param: {}", param);
		
		loginUser = stsDao.xenusLogin(param);
		
		if (logger.isDebugEnabled())
			logger.debug("loginUser: {}", loginUser);


		if(loginUser != null){
			loginResult = new LoginUserSecurity(username, password, true,  true, true, true, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
			loginResult.setMemIndex(	loginUser.get("memIndex"	).toString());
			loginResult.setId(			loginUser.get("id" 			).toString());
			loginResult.setName(		loginUser.get("name"  		).toString());
			loginResult.setMemberType(	loginUser.get("memberType"  ).toString());
			loginResult.setEmail(		loginUser.get("email"  		).toString());
			loginResult.setLastLogin(	loginUser.get("lastLogin"   ).toString());
			loginResult.setRegDate(		loginUser.get("regDate"		).toString());
			loginResult.setModDate(		loginUser.get("modDate"		).toString());
		}		
		return loginResult;
	}
}

