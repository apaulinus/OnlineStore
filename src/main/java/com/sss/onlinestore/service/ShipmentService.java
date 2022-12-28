package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sss.onlinestore.model.Shipment;
import com.sss.onlinestore.repository.ShipmentRepository;

@Service
public class ShipmentService {

	Logger LOG = LoggerFactory.getLogger(ShipmentService.class);
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	public List<Shipment> findShipments(){
		List<Shipment> shipments = new ArrayList<>();
		shipmentRepository.findAll().forEach(shipment -> shipments.add(shipment));
		
		return shipments;
	}
	
	public Optional<Shipment> findShipment(Long id){
		Assert.notNull(id, "Shipment id can not be null.");
		
		return shipmentRepository.findById(id);
	}
	
	public void saveShipment(Shipment shipment) {
		Assert.notNull(shipment, "Shipment can not be null.");
		
		shipmentRepository.save(shipment);
	}
	
	public void updateShipment(Long id, Shipment shipment) {
		Assert.notNull(id, "Shipment id can not be null.");
		Assert.notNull(shipment, "Shipment can not be null.");
		
		shipmentRepository.save(shipment);
	}
	
	public void deleteShipment(Long id) {
		Assert.notNull(id, "Shipment id can not be null.");
		
		shipmentRepository.deleteById(id);
	}
}
