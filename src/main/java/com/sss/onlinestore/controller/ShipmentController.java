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

import com.sss.onlinestore.model.Shipment;
import com.sss.onlinestore.service.ShipmentService;

@RestController
@RequestMapping(value = "/api/rest/secured/shipments")
public class ShipmentController {

	Logger LOG = LoggerFactory.getLogger(ShipmentController.class);

	@Autowired
	private ShipmentService shipmentService;

	@GetMapping()
	public List<Shipment> getShipments() {
		return shipmentService.findShipments();
	}

	@PostMapping()
	public void addShipment(@RequestBody Shipment shipment) {
		Assert.notNull(shipment, "Shipment can not be null.");

		shipmentService.saveShipment(shipment);
	}

	@GetMapping(value = "/{id}")
	public Optional<Shipment> getShipment(@PathVariable Long id) {
		Assert.notNull(id, "Shipment id can not be null.");

		return shipmentService.findShipment(id);
	}

	@PutMapping(value = "/{id}")
	public void updateShipment(@PathVariable Long id, @RequestBody Shipment shipment) {
		Assert.notNull(id, "Shipment id can not be null.");
		Assert.notNull(shipment, "Shipment can not be null.");

		shipmentService.updateShipment(id, shipment);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteShipment(@PathVariable Long id) {
		Assert.notNull(id, "Shipment id can not be null.");

		shipmentService.deleteShipment(id);
	}
}
