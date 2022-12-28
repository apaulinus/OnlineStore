package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sss.onlinestore.model.PurchaseHistory;
import com.sss.onlinestore.repository.PurchaseHistoryRepository;

@Service
public class PurchaseHistoryService {

	Logger LOG = LoggerFactory.getLogger(PurchaseHistoryService.class);
	
	@Autowired
	private PurchaseHistoryRepository purchaseHistoryRepository;
	
	/**
	 * Purchase Histories for all customers
	 * @return
	 */
	public List<PurchaseHistory> findAllPurchaseHistories(){
		List<PurchaseHistory> purchaseHistories = new ArrayList<>();
		purchaseHistoryRepository.findAll().forEach(purchaseHistory -> purchaseHistories.add(purchaseHistory));
		
		return purchaseHistories;
	}
	
	/**
	 * Purchase histories for a single customer
	 * @return
	 */
	public List<PurchaseHistory> findPurchaseHistories(Long customerDetailId){
		List<PurchaseHistory> purchaseHistories = new ArrayList<>();
		purchaseHistoryRepository.findByCustomerDetailId(customerDetailId).forEach(purchaseHistory -> purchaseHistories.add(purchaseHistory));
		
		return purchaseHistories;
	}
	
	public Optional<PurchaseHistory> findPurchaseHistory(Long id){
		return purchaseHistoryRepository.findById(id);
	}
	
	public void savePurchaseHistory(PurchaseHistory purchaseHistory) {
		Assert.notNull(purchaseHistory, "PurchaseHistory can not be null.");
		purchaseHistoryRepository.save(purchaseHistory);
	}
	
	public void updatePurchaseHistory(Long id, PurchaseHistory purchaseHistory) {
		Assert.notNull(id, "PurchaseHistory id can not be null.");
		Assert.notNull(purchaseHistory, "PurchaseHistory can not be null.");
		 
		purchaseHistoryRepository.save(purchaseHistory);
	}
	
	public void deletePurchaseHistory(Long id) {
		Assert.notNull(id, "PurchaseHistory id can not be null.");
		purchaseHistoryRepository.deleteById(id);
	}
}
