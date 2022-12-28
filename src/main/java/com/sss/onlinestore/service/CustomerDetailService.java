package com.sss.onlinestore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.onlinestore.model.CustomerDetail;
import com.sss.onlinestore.repository.CustomerDetailRepository;

@Service
public class CustomerDetailService {

	Logger LOG = LoggerFactory.getLogger(CustomerDetailService.class);
	
	@Autowired
	private CustomerDetailRepository customerDetailRepository;

	public List<CustomerDetail> findAllCustomerDetails(){
		List<CustomerDetail> customerDetails = new ArrayList<>();
		customerDetailRepository.findAll().forEach(customerDetail -> customerDetails.add(customerDetail));
		return customerDetails;
	}
	
	public Optional<CustomerDetail> findCustomerDetail(Long id){
		return customerDetailRepository.findById(id);
	}
	
	public void saveCustomerDetail(CustomerDetail customerDetail) {
		customerDetailRepository.save(customerDetail);
	}
	
	public void updateCustomerDetail(Long id, CustomerDetail customerDetail) {
		customerDetailRepository.save(customerDetail);
	}
	
	public void delete(Long id) {
		customerDetailRepository.deleteById(id);
	}
	
	public LocalDateTime findLastLoginTime(Long id) {
		return customerDetailRepository.findLastLoginTime(id);
	}
}
