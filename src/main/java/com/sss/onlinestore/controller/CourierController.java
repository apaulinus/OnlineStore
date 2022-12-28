package com.sss.onlinestore.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sss.onlinestore.model.Courier;
import com.sss.onlinestore.service.CourierService;

@RestController
public class CourierController {
	
	Logger LOG = LoggerFactory.getLogger(CourierController.class);

	@Autowired
	private CourierService courierService;

	@RequestMapping(value = "/api/couriers")
	public List<Courier> getCouriers() {
		return courierService.findCouriers();
	}

	@RequestMapping(value = "/api/couriers", method = RequestMethod.POST)
	public void addCustomer(@RequestBody Courier courier) {
		courierService.addCourier(courier);
	}
	
	@RequestMapping(value = "/api/couriers/{id}")
	public Optional<Courier> getCustomer(@PathVariable Long id) {
		return courierService.findCourier(id);
	}
	
	@RequestMapping(value = "/api/couriers/{id}", method = RequestMethod.PUT)
	public void updateCustomer(@PathVariable Long id, @RequestBody Courier courier) {
		courierService.updateCourier(id, courier);
	}
	
	@RequestMapping(value = "/api/couriers/{id}", method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable Long id) {
		courierService.delete(id);
	}
}
