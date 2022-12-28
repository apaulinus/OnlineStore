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

import com.sss.onlinestore.model.Country;
import com.sss.onlinestore.service.CountryService;

@RestController
@RequestMapping(value = "/api/rest/countries")
public class CountryController {

	Logger LOG = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	private CountryService countryService;

	@GetMapping()
	public List<Country> getCountries() {
		return countryService.findCountries();
	}

	@GetMapping(value = "/{id}")
	public Optional<Country> getCountry(@PathVariable Long id) {
		Assert.notNull(id, "Country id can not be null.");
		
		return countryService.findCountry(id);
	}

	@PostMapping()
	public void addCountry(@RequestBody Country country) {
		Assert.notNull(country, "Country can not be null.");
		
		countryService.saveCountry(country);
	}
	
	@PutMapping(value = "/{id}")
	public void updateCountry(@PathVariable Long id, @RequestBody Country country) {
		Assert.notNull(id, "Country id can not be null.");
		Assert.notNull(country, "Country can not be null.");
		
		countryService.updateCountry(id, country);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		Assert.notNull(id, "Country id can not be null.");
		
		countryService.delete(id);
	}
}
