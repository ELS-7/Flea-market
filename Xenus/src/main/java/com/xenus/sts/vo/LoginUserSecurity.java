package com.xenus.sts.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

//VO for Security
public class LoginUserSecurity extends User {
	private static final long serialVersionUID = -6227183088052343324L;
	
	protected String 	memIndex;
	protected String 	id;
	protected String 	name;
	protected String	memberType;
	protected String	email;
	protected String	lastLogin;
	protected String	regDate;
	protected String	modDate;
	
	public LoginUserSecurity(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public LoginUserSecurity getLoginUser() {
		return this;
	}


	public String getMemIndex() {
		return memIndex;
	}

	public void setMemIndex(String memIndex) {
		this.memIndex = memIndex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
