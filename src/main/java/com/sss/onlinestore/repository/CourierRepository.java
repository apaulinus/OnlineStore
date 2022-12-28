package com.sss.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sss.onlinestore.model.Courier;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Long> {

	public Courier findByPurchaseHistoriesId(Long id);
}
