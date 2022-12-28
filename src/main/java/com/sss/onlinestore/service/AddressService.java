package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sss.onlinestore.model.Address;
import com.sss.onlinestore.repository.AddressRepository;

@Service
public class AddressService {

	Logger LOG = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	private AddressRepository addressRepository;
	
	public List<Address> findAddresses(){
		List<Address> addresses = new ArrayList<>();
		
		addressRepository.findAll().forEach(address -> addresses.add(address));
		return addresses;
	}
	
	public Optional<Address> findAddress(Long id) {
		return addressRepository.findById(id);
	}
	
	@Transactional
	public void addAddress(Address address) {
		addressRepository.save(address);
	}
	
	public void updateAddress(Long id, Address address) {
		addressRepository.save(address);
	}
	
	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}
}
