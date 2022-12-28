package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.onlinestore.model.LoginHistory;
import com.sss.onlinestore.repository.LoginHistoryRepository;

@Service
public class LoginHistoryService {
	
	Logger LOG = LoggerFactory.getLogger(LoginHistoryService.class);
			
	@Autowired
	LoginHistoryRepository loginHistoryRepository;
	
	/**
	 * Find All login histories
	 * @return
	 */
	public List<LoginHistory> findAllLoginHistories(){
		List<LoginHistory> loginHistories = new ArrayList<>();
		loginHistoryRepository.findAll().forEach(loginHistory -> loginHistories.add(loginHistory));
		return loginHistories;
	}
	
	/**
	 * Find all login histories for a single customer.
	 * @param customerId
	 * @return
	 */
	public List<LoginHistory> findOneCustomersLoginHistories(Long customerId){
		List<LoginHistory> loginHistories = new ArrayList<>();
		loginHistoryRepository.findOneCustomersLoginHistories(customerId).forEach(loginHistory -> loginHistories.add(loginHistory));
		return loginHistories;
	}
	
	/**
	 * Find a single login history for a single customer.
	 * @param id
	 * @return
	 */
	public Optional<LoginHistory> findLoginHistory(Long id){
		return loginHistoryRepository.findById(id);
	}
	
	public void addLoginHistory(LoginHistory loginHistory) {
		loginHistoryRepository.save(loginHistory);
	}
	
	public void updateLoginHistory(Long id, LoginHistory loginHistory) {
		loginHistoryRepository.save(loginHistory);
	}
	
	public void delete(Long id) {
		loginHistoryRepository.deleteById(id);
	}
}
