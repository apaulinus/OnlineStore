package com.sss.onlinestore.response;

import java.time.LocalDateTime;
import java.util.Set;

import com.sss.onlinestore.model.Role;

public class LoginResponse extends RestResponse {
	private String FirstName;
	private String lastName;
	private String token;
	private String userName;
	private LocalDateTime lastLogin;
	private Set<Role> authorities;
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Set<Role> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}
}
