package com.sss.onlinestore.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sss.onlinestore.model.Customer;
import com.sss.onlinestore.model.CustomerDetail;
import com.sss.onlinestore.model.Login;
import com.sss.onlinestore.response.LoginResponse;
import com.sss.onlinestore.util.OnlineStoreUtil;

@Service
public class LoginService {

	Logger LOG = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerDetailService customerDetailService;
	
	/*
	 * Customer log in to the store
	 * return loginResponse
	 */
	public LoginResponse login(Login login) {
		LoginResponse loginResponse = new LoginResponse();
		Assert.notNull(login, "Login can not be null.");
		Assert.hasText(login.getUserName(), "User name must have a value.");
		Assert.hasText(login.getPassword(), "Password must have a value.");
		
		Optional<Customer> optionalCustomer = customerService.findByUserName(login.getUserName());
		
		optionalCustomer.orElseThrow(() -> new UsernameNotFoundException("No user matches the user name"));
		optionalCustomer.ifPresent(customer -> {
			MDC.put("firstName", customer.getFirstName());
			MDC.put("lastName", customer.getLastName());
			
			LOG.info("request: user/login for user: {} {}", customer.getFirstName(), customer.getLastName());
			
			try {
				if (customer.getPassword().equals(OnlineStoreUtil.passwordDigest(login.getPassword()))) {
					LOG.info("logging successful for customer {} {}", customer.getFirstName(), customer.getLastName());
					
					LocalDateTime lastLogin = customerDetailService.findLastLoginTime(customer.getId());
					
					//Set first name and last name of the log in user.
					loginResponse.setFirstName(customer.getFirstName());
					loginResponse.setLastName(customer.getLastName());
					loginResponse.setLastLogin(lastLogin);
					loginResponse.setSuccess(true);
					
					//Set CustomerDetail bean for onward update of the Customer detail table.
					CustomerDetail customerDetail = new CustomerDetail();
					customerDetail.setId(customer.getId());
					customerDetail.setLastLogin(LocalDateTime.now());
					customerDetail.setLastUpdated(LocalDateTime.now());
					customerDetailService.updateCustomerDetail(customer.getId(), customerDetail);
				}
			} catch (Exception e) {
				LOG.error("Exception occured digesting password");
				e.printStackTrace();
			}
		});
		return loginResponse;
	}
}
