package com.sss.onlinestore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sss.onlinestore.admin.model.Users;

import lombok.Data;

@Entity
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "customerId", nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "usersId", nullable = false)
	private Users users;
		
}
