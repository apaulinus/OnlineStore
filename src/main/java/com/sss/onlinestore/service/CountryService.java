package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.onlinestore.model.Country;
import com.sss.onlinestore.repository.CountryRepository;

@Service
public class CountryService {
	
	Logger LOG = LoggerFactory.getLogger(CountryService.class);
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> findCountries(){
		List<Country> countries = new ArrayList<>();
		countryRepository.findAll().forEach(country -> countries.add(country));
		return countries;
	}
	
	public Optional<Country> findCountry(Long id){
		return countryRepository.findById(id);
	}
	
	public void saveCountry(Country country) {
		countryRepository.save(country);
	}
	
	public void updateCountry(Long id, Country country) {
		countryRepository.save(country);
	}
	
	public void delete(Long id) {
		countryRepository.deleteById(id);
	}
}
