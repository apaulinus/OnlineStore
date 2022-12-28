package com.sss.onlinestore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class State {

	@Id
	@GeneratedValue
	private Long id;
	
	private String abbreviation;
	private String name;

	@ManyToOne
	private Country country;
	
}
