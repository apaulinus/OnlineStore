package com.sss.onlinestore.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sss.onlinestore.model.CustomerDetail;
import com.sss.onlinestore.service.CustomerDetailService;

@RestController
@RequestMapping(value = "/api/rest/customerDetails")
public class CustomerDetailController {

	Logger LOG = LoggerFactory.getLogger(CustomerDetailController.class);

	@Autowired
	private CustomerDetailService customerDetailService;

	@GetMapping()
	public List<CustomerDetail> getShipments() {
		return customerDetailService.findAllCustomerDetails();
	}

	@PostMapping()
	public void addCustomerDetail(@RequestBody CustomerDetail customerDetail) {
		customerDetailService.saveCustomerDetail(customerDetail);
	}
	
	@GetMapping(value = "/{id}")
	public Optional<CustomerDetail> getShipment(@PathVariable Long id) {
		return customerDetailService.findCustomerDetail(id);
	}
	
	@PutMapping(value = "/{id}")
	public void updateShipment(@PathVariable Long id, @RequestBody CustomerDetail customerDetail) {
		customerDetailService.updateCustomerDetail(id, customerDetail);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteCustomerDetail(@PathVariable Long id) {
		customerDetailService.delete(id);
	}
}
