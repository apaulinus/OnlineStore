package com.sss.onlinestore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Shipment {

	@Id
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name="id")
	private PurchaseHistory purchaseHistory;
	
	@ManyToOne
	private ShipmentType shipmentType;
	
	@Column(name = "tracking_number")
	private String trackingNumber;
	
	@Column(name="shipment_date_time")
	private LocalDateTime shipmentDateTime;
}
