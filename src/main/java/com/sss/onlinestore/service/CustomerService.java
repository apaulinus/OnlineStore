package com.sss.onlinestore.service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.sss.onlinestore.response.CustomerPurchaseHistoryResponse;
import com.sss.onlinestore.model.Customer;
import com.sss.onlinestore.model.CustomerDetail;
import com.sss.onlinestore.repository.CustomerRepository;
import com.sss.onlinestore.util.OnlineStoreUtil;

@Service
public class CustomerService {

	Logger LOG = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findCustomers(){
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customer -> customers.add(customer));
		
		return customers;
	}
	
	public Customer findCustomer(Long id){
		Assert.notNull(id, "Customer id can not be null.");
		
		Optional<Customer> customerOpt = customerRepository.findById(id);
		
		if(customerOpt.isPresent()) {
			return customerOpt.get();
		}
		return new Customer();
	}
	
	@Transactional
	public Long saveCustomer(Customer customer) throws Exception{
		Assert.notNull(customer, "Customer can not be null.");
		Assert.hasText(customer.getPassword(), "Password must have a value.");
		Assert.hasText(customer.getUserName(), "User name must have a value");
		Assert.hasText(customer.getFirstName(), "First name must have a value.");
		Assert.hasText(customer.getLastName(), "Last name must have a value.");

		customer.setPassword(OnlineStoreUtil.passwordDigest(customer.getPassword()));

		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setCreatedOn(LocalDateTime.now());
		customerDetail.setLastUpdated(LocalDateTime.now());

		customerDetail.setCustomer(customer);
		customer.setCustomerDetail(customerDetail);

		findByUserName(customer.getUserName()).orElse(
			customerRepository.save(customer)
		);
		
		MDC.put("createdDate", customerDetail.getCreatedOn().toString());
		
		return customer.getId();
	}
	
	public Optional<Customer> findByUserName(String userName) {
		Assert.hasText(userName, "User name can not be null.");
		return customerRepository.findByUserName(userName);
	}
	
	@Transactional 
	public void updateCustomer(Long id, Customer customer) {
		Assert.notNull(id, "Customer id can not be null.");
		Assert.notNull(customer, "Customer can not be null.");
		customerRepository.save(customer);
	}
	
	public Customer updateSpecificCustomerFields(Long id, Map<String, String> customers) {
		Assert.notNull(id, "Customer id can not be null.");
		Assert.notNull(customers, "Customers can not be null");
		
		Optional<Customer> customerOpt = customerRepository.findById(id);
		
		if(customerOpt.isPresent()) {
			Customer existingCustomer = customerOpt.get();
			
			customers.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Customer.class, key);
				if(field != null) {
					field.setAccessible(true);
					ReflectionUtils.setField(field, existingCustomer, value);
				}
			});
			
			return customerRepository.save(existingCustomer);
		}
		
		return new Customer();
	}
	
	public void deleteCustomer(Long id) {
		Assert.notNull(id, "Customer id can not be null.");
		customerRepository.deleteById(id);
	}
	
	public List<CustomerPurchaseHistoryResponse> findCustomerPurchaseHistory(Long id){
	  Assert.notNull(id, "Customer id can not be null."); 
	  return customerRepository.findCustomerPurchaseHistory(id); 
	}
	 
}
