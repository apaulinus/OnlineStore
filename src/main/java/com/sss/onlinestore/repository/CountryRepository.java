package com.sss.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sss.onlinestore.model.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>{

	public Country findByStatesId(Long id);
	
	public Country findByAddressesId(Long id);
}
