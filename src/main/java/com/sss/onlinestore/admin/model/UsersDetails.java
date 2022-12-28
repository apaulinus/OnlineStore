package com.sss.onlinestore.admin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users_details")
public class UsersDetails {
	@Id
	@Column(unique = true, nullable = false)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@MapsId
	@JoinColumn(name = "id", nullable = false)
//	@JsonManagedReference
    private Users users;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@Column(name = "last_updated")
	private LocalDateTime lastUdated;

	
	public UsersDetails() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return users;
	}

	public void setUser(Users users) {
		this.users = users;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public LocalDateTime getLastUdated() {
		return lastUdated;
	}

	public void setLastUdated(LocalDateTime lastUdated) {
		this.lastUdated = lastUdated;
	}
}
