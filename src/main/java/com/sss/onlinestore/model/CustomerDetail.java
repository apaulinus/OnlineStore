package com.sss.onlinestore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer_detail")
@Data
public class CustomerDetail {

	@Id
	@Column(unique = true, nullable = false)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@MapsId
	@JoinColumn(name = "id", nullable = false)
//	@JsonManagedReference
    private Customer customer;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@Column(name = "last_updated")
	private LocalDateTime lastUpdated;

	public CustomerDetail() {

	}

}
