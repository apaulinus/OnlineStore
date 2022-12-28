package com.sss.onlinestore.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken{

	/**
	 * generated serial version id
	 */
	private static final long serialVersionUID = -4632247987691285539L;
	private String token;
	
	
	public JwtAuthenticationToken(String token) {
		super(null, null);
		this.token = token;
	}
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	
}
