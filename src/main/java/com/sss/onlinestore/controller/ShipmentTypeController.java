package com.sss.onlinestore.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sss.onlinestore.model.ShipmentType;
import com.sss.onlinestore.service.ShipmentTypeService;

@RestController
@RequestMapping(value = "/api/rest/secured/shipmentTypes")
public class ShipmentTypeController {

	Logger LOG = LoggerFactory.getLogger(ShipmentTypeController.class);

	@Autowired
	private ShipmentTypeService shipmentTypeService;

	@GetMapping()
	public List<ShipmentType> getShipmentTypes() {
		return shipmentTypeService.findShipmentTypes();
	}

	@PostMapping()
	public void addShipment(@RequestBody ShipmentType shipmentType) {
		Assert.notNull(shipmentType, "Shipment Type can not be null.");

		shipmentTypeService.saveShipmentType(shipmentType);
	}

	@GetMapping(value = "/{id}")
	public Optional<ShipmentType> getShipmentType(@PathVariable Long id) {
		Assert.notNull(id, "Shipment Type id can not be null.");

		return shipmentTypeService.findShipmentType(id);
	}

	@PutMapping(value = "/{id}")
	public void updateShipmentType(@PathVariable Long id, @RequestBody ShipmentType shipmentType) {
		Assert.notNull(id, "Shipment Type id can not be null.");
		Assert.notNull(shipmentType, "Shipment Type can not be null.");

		shipmentTypeService.updateShipmentType(id, shipmentType);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteShipmentType(@PathVariable Long id) {
		Assert.notNull(id, "Shipment Type id can not be null.");

		shipmentTypeService.deleteShipmentType(id);
	}
}
