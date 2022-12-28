package com.sss.onlinestore.model;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class Login {
	private long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDateTime lastLogin;
	
	private Set<Role> authorities;

}
