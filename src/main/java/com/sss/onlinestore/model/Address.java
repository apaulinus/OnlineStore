package com.sss.onlinestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
@Data
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String address1;
	private String address2;
	private String city;
	private Long stateId;
	private String phone;
	
	@Column(name = "zip_code")
	private String zipCode;
}
