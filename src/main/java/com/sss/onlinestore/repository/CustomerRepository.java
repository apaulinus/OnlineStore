package com.sss.onlinestore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sss.onlinestore.response.CustomerPurchaseHistoryResponse;
import com.sss.onlinestore.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
                    
	@Query("select new Customer(c.id, c.password, c.firstName, c.lastName) from Customer c where c.userName = ?1")
	public Optional<Customer> findByUserName(String userName);

	@Query("select new CustomerPurchaseHistory(c.firstName, c.lastName, ph.purchaseItem, ph.purchaseDate) from Customer c JOIN c.purchaseHistory ph")
	public List<CustomerPurchaseHistoryResponse> findCustomerPurchaseHistory(Long id);
 
}
