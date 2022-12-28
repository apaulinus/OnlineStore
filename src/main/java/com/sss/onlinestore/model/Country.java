package com.sss.onlinestore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Country {
	
	@Id
	@GeneratedValue
	private Long id;
	private String abbreviation;
	private String name;

	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_country_id")
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(targetEntity=State.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_country_id")
	private List<State> states = new ArrayList<>();
	
}
