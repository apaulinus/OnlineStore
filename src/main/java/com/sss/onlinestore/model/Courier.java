package com.sss.onlinestore.model;

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
public class Courier {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(targetEntity=PurchaseHistory.class, cascade=CascadeType.ALL)
	@JoinColumn(name="fk_courier_id")
	List<PurchaseHistory> purchaseHistories;
	
	private String abbreviation;
	
	private String name;

	public Courier(Long id, List<PurchaseHistory> purchaseHistories, String abbreviation, String name) {
		super();
		this.id = id;
		this.purchaseHistories = purchaseHistories;
		this.abbreviation = abbreviation;
		this.name = name;
	}

	public Courier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
