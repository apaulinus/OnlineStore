package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.onlinestore.model.Courier;
import com.sss.onlinestore.repository.CourierRepository;

@Service
public class CourierService {
	
	Logger LOG = LoggerFactory.getLogger(CourierService.class);
	
	@Autowired
	private CourierRepository courierRepository;
	
	public List<Courier> findCouriers(){
		List<Courier> couriers = new ArrayList<>();
		courierRepository.findAll().forEach(courier -> couriers.add(courier));
		return couriers;
	}
	
	public void addCourier(Courier courier) {
		courierRepository.save(courier);
	}
	
	public Optional<Courier> findCourier(Long id){
		return courierRepository.findById(id);
	}
	
	public void updateCourier(Long id, Courier courier) {
		courierRepository.save(courier);
	}
	
	public void delete(Long id) {
		courierRepository.deleteById(id);
	}
}
