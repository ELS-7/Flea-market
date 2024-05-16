package com.xenus.sts.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class IntegrationAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 5180020659133149729L;
	private String  accessKey;
	
	public IntegrationAuthenticationToken (Object principal, Object credentials) {
		super(null, null);
	}
	
	public IntegrationAuthenticationToken(Object principal, Object credentials, String accessKey) {
		super(principal, credentials);
		this.accessKey = accessKey;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
