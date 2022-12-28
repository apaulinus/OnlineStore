package com.sss.onlinestore.security.model;

public class JwtUser {

	private String username;
	private long id;
	private String role;
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}
}
