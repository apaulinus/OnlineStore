package com.sss.onlinestore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sss.onlinestore.model.LoginHistory;
import com.sss.onlinestore.response.RestResponse;
import com.sss.onlinestore.service.LoginHistoryService;

@Controller
@RequestMapping(value = "/api/rest/secured/loginHistories")
public class LoginHistoryController {

	Logger LOG = LoggerFactory.getLogger(LoginHistoryController.class);

	@Autowired
	private LoginHistoryService loginHistoryService;
	
	@GetMapping()
	public List<LoginHistory> getLoginHistories() {
		MDC.put("Get", "In getLoginHistories() method");
		
		RestResponse restResponse = new RestResponse();
		LoginHistory loginHistory = new LoginHistory();
		List<LoginHistory> loginHistories = new ArrayList<>();
		
		try {
			return loginHistoryService.findAllLoginHistories();
		}
		catch(Exception e) {
			LOG.error(e.getMessage());
		}
		
		restResponse.addError("An error occured, please contact the server administrator");
		loginHistory.setRestResponse(restResponse);
		loginHistories.add(loginHistory);
		return loginHistories;
	}

	@GetMapping(value = "/customer/{customerId}")
	public List<LoginHistory> getOneCustomersLoginHistories(@PathVariable Long customerId){
		return loginHistoryService.findOneCustomersLoginHistories(customerId);
	}

	@GetMapping("/{id}")
	public Optional<LoginHistory> getLoginHistory(@PathVariable Long id) {
		return loginHistoryService.findLoginHistory(id);
	}
	
	@PostMapping()
	public void addLoginHistory(@RequestBody LoginHistory loginHistory) {
		loginHistoryService.addLoginHistory(loginHistory);
	}
	
	@PutMapping(value = "/{id}")
	public void updateLoginHistory(@PathVariable Long id, @RequestBody LoginHistory courier) {
		loginHistoryService.updateLoginHistory(id, courier);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteLoginHistory(@PathVariable Long id) {
		loginHistoryService.delete(id);
	}
}
