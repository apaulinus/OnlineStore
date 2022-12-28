package com.sss.onlinestore.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="shipment_type")
@Data
public class ShipmentType {

	@Id
	@GeneratedValue
	private Long id;
	
	private String abbreviation;
	private String name;
	
	@OneToMany
	@JoinColumn(name="fk_shipment_type_id")
	private List<PurchaseHistory> purchaseHistory;

}
