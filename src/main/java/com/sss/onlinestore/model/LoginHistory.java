package com.sss.onlinestore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sss.onlinestore.response.RestResponse;

import lombok.Data;

@Entity
@Table(name="login_history")
@Data
public class LoginHistory {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="login_date_time")
	private LocalDateTime loginDateTime;
	
	@Column(name="login_device_type")
	private String loginDeviceType;
	
	@ManyToOne
	private Customer customer;
	
	RestResponse restResponse;

	public LoginHistory() {
		
	}
	
	public LoginHistory(Long id, LocalDateTime loginDateTime, String loginDeviceType, Customer customer) {
		super();
		this.id = id;
		this.loginDateTime = loginDateTime;
		this.loginDeviceType = loginDeviceType;
		this.customer = customer;
	}

}
