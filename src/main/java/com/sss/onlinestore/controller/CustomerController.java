package com.sss.onlinestore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sss.onlinestore.response.CustomerPurchaseHistoryResponse;
import com.sss.onlinestore.model.Customer;
import com.sss.onlinestore.response.RestResponse;
import com.sss.onlinestore.service.CustomerService;

@Controller
@EnableTransactionManagement
@RequestMapping(value = "/api/rest/customers")
public class CustomerController {

	Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	/**
	 * A very basic get RESTful web service for getting the list of all the customers.
	 * @return
	 */
	@GetMapping()
	public List<Customer> getCustomers() {
		MDC.put("Get", "In getCustomers method");
		try {
			LOG.info("About to retrieve Customers");
			return customerService.findCustomers();
		} catch (Exception e) {
			LOG.error("Error occured", e);
		}

		return null;
	}

	/**
	 * A very basic get RESTful web service for getting a specific customer based on id.
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
		MDC.put("Get", "In getCustomer method");
		
		Assert.notNull(id, "Customer Id can not be null.");

		try {
			LOG.info(String.format("Retrieving a customer using the customer id: {} ", id));
			Customer customer = customerService.findCustomer(id);
			
			ResponseEntity.status(HttpStatus.FOUND);
			return ResponseEntity.ok().body(customer);
		} catch (Exception e) {
			LOG.error("Internal Server Error occured", e);
		}
			
		ResponseEntity.status(HttpStatus.BAD_REQUEST);
		return ResponseEntity.badRequest().build();
	}
	
	/**
	 * A very basic get RESTful web service for getting the purchase history of a 
	 * specific customer based on id.
	 * @return
	 */
	@GetMapping(value = "/purchaseHistory/{id}")
	public ResponseEntity<List<CustomerPurchaseHistoryResponse>> getCustomerPurchaseHistory(@PathVariable("id") Long id) {
		
		MDC.put("Get", "In getCustomerPurchaseHistory");
		List<CustomerPurchaseHistoryResponse> customerPuchaseHistories = new ArrayList<>();
		
		Assert.notNull(id, "Customer's id can not be null.");
		
		try {
			LOG.info(String.format("Retrieving the purchase history of a customer using his id: {}", id));
			customerPuchaseHistories = customerService.findCustomerPurchaseHistory(id);
			return ResponseEntity.ok().body(customerPuchaseHistories);
		} catch (Exception e) {
			LOG.error("Error occured", e);
		}
		
		LOG.info("Create an error message to return to Client.");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	/**
	 * A very basic post RESTful web service for adding a customer to the storage.
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<RestResponse> addCustomer(@RequestBody Customer customer) {
		MDC.put("Post", "In addCustomer method");
		
		Assert.notNull(customer, "Customer can not be null.");
		Assert.hasText(customer.getPassword(), "Password must have a value.");
		Assert.hasText(customer.getUserName(), "User name must have a value");
		Assert.hasText(customer.getFirstName(), "First name must have a value.");
		Assert.hasText(customer.getLastName(), "Last name must have a value.");
		Assert.hasText(customer.getEmail(), "Email must have a value.");
		
		RestResponse restResponse = new RestResponse();
		
		System.out.println(customer.toString());
		Long customerId = null;
		try {
			
			LOG.info(String.format("Inserting a customer: {} ", customer.toString()));
			customerId = customerService.saveCustomer(customer);
		} catch (Exception e) {
			LOG.error("Internal Server Error occured", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		if(customerId != null) {
			restResponse.setSuccess(Boolean.TRUE);
			return ResponseEntity.ok().body(restResponse);
		}
		else {
			restResponse.setSuccess(Boolean.FALSE);
			restResponse.addError(String.format("User %s name already in use; please enter a different user name.", customer.getUserName()));
			return ResponseEntity.badRequest().body(restResponse);
		}
	}

	/**
	 * A very basic put RESTful web service for updating a customer in persistent storage.
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		
		MDC.put("Put", "In updateCustomer");
		Assert.notNull(customer, "Customer can not be null.");
		Assert.notNull(id, "id can not be null.");
		
		try {
			LOG.info(String.format("updating customer id: {}", id));
			customerService.updateCustomer(id, customer);
		}
		catch(Exception e) {
			LOG.error("Internal Server error occured", e);
		}
	}
	
	/**
	 * A very basic patch RESTful web service for updating specific fields in a customer in persistent storage.
	 * @return
	 */
	@PatchMapping(value = "/{id}")
	public void updateSpecifiedCustomerFields(@PathVariable Long id, @RequestBody Map<String, String> customers) {
		Assert.notNull(customers, "Customers can not be null.");
		customerService.updateSpecificCustomerFields(id, customers);
	}

	/**
	 * A very basic delete RESTful web service for deleting a specific customer based on id.
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		Assert.notNull(id, "Customer id can not be null.");
		customerService.deleteCustomer(id);
	}
}
