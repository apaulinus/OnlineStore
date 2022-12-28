package com.sss.onlinestore.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sss.onlinestore.model.CustomerDetail;

@Repository
public interface CustomerDetailRepository extends CrudRepository<CustomerDetail, Long> {

	@Query("select lastLogin from CustomerDetail where id = ?1")
	public LocalDateTime findLastLoginTime(long id);
}
