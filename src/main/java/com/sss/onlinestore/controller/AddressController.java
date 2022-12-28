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

import com.sss.onlinestore.model.Address;
import com.sss.onlinestore.service.AddressService;

@RestController
public class AddressController {

	Logger LOG = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value="/api/addresses")
	public List<Address> getAddresses(){
		return addressService.findAddresses();
	}
	
	@RequestMapping(value="/api/addresses", method = RequestMethod.POST)
	public void addAddress(@RequestBody Address address) {
		addressService.addAddress(address);
	}
	
	@RequestMapping(value="/api/addresses/{id}", method = RequestMethod.GET)
	public Optional<Address> getAddress(@PathVariable Long id) {
		return addressService.findAddress(id);
	}
	
	@RequestMapping(value="/api/addresses/{id}", method=RequestMethod.PUT)
	public void updateAddress(@PathVariable Long id, @RequestBody Address address) {
		addressService.updateAddress(id, address);
	}
	
	@RequestMapping(value="/api/addresses/{id}", method=RequestMethod.DELETE)
	public void deleteAddress(@PathVariable Long id) {
		addressService.deleteAddress(id);
	}
}
