package com.sss.onlinestore.security.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 4278786730221737388L;
	private String userName;
	private String token;
	private Long id;
	
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetails(String userName, Long id, String token, List<GrantedAuthority> authorities) {
		this.userName = userName;
		this.token = token;
		this.id = id;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	/*
	 * public String getUserName() { return userName; }
	 */
	public String getToken() {
		return token;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
