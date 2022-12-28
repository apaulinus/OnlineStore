package com.sss.onlinestore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sss.onlinestore.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findByNameContaining(String name);
	
	List<Product> findByPriceLessThan(double price);

}
