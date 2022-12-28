package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sss.onlinestore.model.ShipmentType;
import com.sss.onlinestore.repository.ShipmentTypeRepository;

@Service
public class ShipmentTypeService {

	Logger LOG = LoggerFactory.getLogger(ShipmentTypeService.class);
	
	@Autowired
	private ShipmentTypeRepository shipmentTypeRepository;
	
	public List<ShipmentType> findShipmentTypes(){
		List<ShipmentType> shipmentTypes = new ArrayList<>();
		shipmentTypeRepository.findAll().forEach(shipmentType -> shipmentTypes.add(shipmentType));
		
		return shipmentTypes;
	}
	
	public Optional<ShipmentType> findShipmentType(Long id){
		Assert.notNull(id, "Shipment Type id can not be null.");
		
		return shipmentTypeRepository.findById(id);
	}
	
	public void saveShipmentType(ShipmentType shipmentType) {
		Assert.notNull(shipmentType, "Shipment Type can not be null.");
		
		shipmentTypeRepository.save(shipmentType);
	}
	
	public void updateShipmentType(Long id, ShipmentType shipmentType) {
		Assert.notNull(shipmentType, "Shipment Type can not be null.");
		Assert.notNull(id, "Shipment Type id can not be null.");
		
		shipmentTypeRepository.save(shipmentType);
	}
	
	public void deleteShipmentType(Long id) {
		Assert.notNull(id, "Shipment Type id can not be null.");
		
		shipmentTypeRepository.deleteById(id);
	}
}
