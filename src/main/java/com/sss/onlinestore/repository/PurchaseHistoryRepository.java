package com.sss.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sss.onlinestore.model.PurchaseHistory;

public interface PurchaseHistoryRepository extends CrudRepository<PurchaseHistory, Long> {

	@Query("SELECT new PurchaseHistory(ph.id, ph.shipped, ph.shippedBy, ph.purchaseDate, ph.courier, ph.customer) FROM PurchaseHistory ph where ph.customer.id = ?1")
	public List<PurchaseHistory> findByCustomerDetailId(Long id);
}
