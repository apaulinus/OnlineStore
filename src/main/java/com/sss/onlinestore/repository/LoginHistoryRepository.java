package com.sss.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sss.onlinestore.model.LoginHistory;

public interface LoginHistoryRepository extends CrudRepository<LoginHistory, Long> {

	@Query("select new LoginHistory(lh.id, lh.loginDateTime, lh.loginDeviceType, lh.customer) from LoginHistory lh where lh.customer.id = ?1")
	public List<LoginHistory> findOneCustomersLoginHistories(Long customersId);
}
