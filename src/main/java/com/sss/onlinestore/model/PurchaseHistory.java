package com.sss.onlinestore.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "purchase_history")
@Data
public class PurchaseHistory {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "shipped_by")
	private String shippedBy;

	@OneToOne(optional = true, mappedBy = "purchaseHistory", cascade = CascadeType.ALL)
	private Shipment shipment;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Courier courier;

	@ManyToOne
	private ShipmentType shipmentType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm")
	private LocalDateTime purchaseDate;
	
	private String purchaseItem;
	private boolean shipped;

	public PurchaseHistory() {
		
	}
	public PurchaseHistory(Long id, boolean shipped, String shippedBy, LocalDateTime purchaseDate, Courier courier, Customer customer) {
		this.id = id;
		this.shippedBy = shippedBy;
		this.customer = customer;
		this.courier = courier;
		this.purchaseDate = purchaseDate;
		this.shipped = shipped;
	}

		public void setPurchaseDate(String purchaseDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.purchaseDate = LocalDateTime.parse(purchaseDate, formatter);
	}

}
