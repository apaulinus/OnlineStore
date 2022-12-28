package com.sss.onlinestore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sss.onlinestore.model.CustomUserDetails;
import com.sss.onlinestore.model.Customer;
import com.sss.onlinestore.repository.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Customer> optionalCustomer = customerRepository.findByUserName(username);
		optionalCustomer.orElseThrow(() -> new UsernameNotFoundException(String.format("User name %s not found", username)));
		return optionalCustomer
				.map(customer -> new CustomUserDetails(customer))
				.get();
	}

}
