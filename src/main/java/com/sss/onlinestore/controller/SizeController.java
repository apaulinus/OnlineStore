package com.sss.onlinestore.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sss.onlinestore.model.Size;
import com.sss.onlinestore.service.SizeService;

@RestController
public class SizeController {
	
	Logger LOG = LoggerFactory.getLogger(SizeController.class);

	@Autowired
	private SizeService sizeService;
	
	@RequestMapping(value="/api/sizes")
	public List<Size> getStates(){
		return sizeService.findSizes();
	}
	
	@RequestMapping(value="/api/sizes/{id}")
	public Optional<Size> getState(@PathVariable Long id) {
		Assert.notNull(id, "Size id can not be null.");
		
		return sizeService.findSize(id);
	}
	
	@RequestMapping(value="/api/sizes", method = RequestMethod.POST)
	public void addSize(@RequestBody Size size) {
		Assert.notNull(size, "Size can not be null.");
		
		sizeService.saveSize(size);
	}
	
	@RequestMapping(value="/api/sizes/{id}", method=RequestMethod.PUT)
	public void updateState(@PathVariable Long id, @RequestBody Size size) {
		Assert.notNull(id, "Size id can not be null.");
		Assert.notNull(size, "Size can not be null.");
		
		sizeService.updateSize(id, size);
	}
	
	@RequestMapping(value="/api/sizes/id", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		sizeService.delete(id);
	}
}
