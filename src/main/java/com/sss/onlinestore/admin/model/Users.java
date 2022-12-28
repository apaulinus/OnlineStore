package com.sss.onlinestore.admin.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sss.onlinestore.model.Role;

@Entity
public class Users {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_user_id")
	@JsonIgnore
	@JsonBackReference
	Set<Role> roles;
	
	@OneToOne(optional = true, mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnore
    private UsersDetails usersDetails;
	
	@Column(name="first_name")
	String firstName;
	
	@Column(name="last_name")
	String lastName;
	
	@Column(name="user_name")
	String userName;
	
	String password;
	
	@Column(name="computer_generated_password")
	String computerGeneratedPassword;
	
	public Users() {

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public UsersDetails getUserDetails() {
		return usersDetails;
	}
	public void setUserDetails(UsersDetails usersDetails) {
		this.usersDetails = usersDetails;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getComputerGeneratedPassword() {
		return computerGeneratedPassword;
	}
	public void setComputerGeneratedPassword(String computerGeneratedPassword) {
		this.computerGeneratedPassword = computerGeneratedPassword;
	}
}
