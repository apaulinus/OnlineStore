package com.sss.onlinestore.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sss.onlinestore.model.PurchaseHistory;
import com.sss.onlinestore.service.PurchaseHistoryService;

@RestController
@RequestMapping(value = "/api/rest/secured/purchaseHistories")
public class PurchaseHistoryController {

	Logger LOG = LoggerFactory.getLogger(PurchaseHistoryController.class);

	@Autowired
	private PurchaseHistoryService purchaseHistoryService;

	/**
	 * Get all purchase histories for all customers.
	 * 
	 * @return
	 */
	@GetMapping()
	public List<PurchaseHistory> getAllPurchasHistories() {
		return purchaseHistoryService.findAllPurchaseHistories();
	}

	/**
	 * Get purchase histories for a single customer
	 * 
	 * @param customerId
	 * @return
	 */
	@GetMapping("/{customerId}")
	public List<PurchaseHistory> getPurchasHistories(Long customerId) {
		return purchaseHistoryService.findPurchaseHistories(customerId);
	}

	@PostMapping()
	public void addPurchaseHistory(@RequestBody PurchaseHistory purchaseHistory) {
		Assert.notNull(purchaseHistory, "PurchaseHistory can not be null.");
		purchaseHistoryService.savePurchaseHistory(purchaseHistory);
	}

	/**
	 * get a single purchase history for one customer
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Optional<PurchaseHistory> getPurchaseHistory(@PathVariable Long id) {
		Assert.notNull(id, "PurchaseHistory id can not be null.");
		return purchaseHistoryService.findPurchaseHistory(id);
	}

	@PutMapping(value = "/{id}")
	public void updatePurchaseHistory(@PathVariable Long id, @RequestBody PurchaseHistory purchaseHistory) {
		Assert.notNull(id, "PurchaseHistory id can not be null.");
		Assert.notNull(purchaseHistory, "PurchaseHistory can not be null.");

		purchaseHistoryService.updatePurchaseHistory(id, purchaseHistory);
	}

	@DeleteMapping(value = "/api/purchaseHistories/{id}")
	public void deletePurchaseHistory(@PathVariable Long id) {
		Assert.notNull(id, "PurchaseHistory id can not be null.");

		purchaseHistoryService.deletePurchaseHistory(id);
	}
}
