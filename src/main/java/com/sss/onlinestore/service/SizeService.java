package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sss.onlinestore.model.Size;
import com.sss.onlinestore.repository.SizeRepository;

@Service
public class SizeService {
	Logger LOG = LoggerFactory.getLogger(SizeService.class);
	
	@Autowired
	private SizeRepository sizeRepository;
	
	public List<Size> findSizes(){
		List<Size> sizes = new ArrayList<>();
		sizeRepository.findAll().forEach(size -> sizes.add(size));
		return sizes;
	}
	
	public Optional<Size> findSize(Long id){
		return sizeRepository.findById(id);
	}
	
	public void saveSize(Size size) {
		Assert.notNull(size, "Size can not be null.");
		
		sizeRepository.save(size);
	}
	
	public void updateSize(Long id, Size size) {
		Assert.notNull(id, "Size id can not be null.");
		Assert.notNull(size, "Size can not be null.");
		
		
		sizeRepository.save(size);
	}
	
	public void delete(Long id) {
		Assert.notNull(id, "Size id can not be null.");
		
		sizeRepository.deleteById(id);
	}
}
