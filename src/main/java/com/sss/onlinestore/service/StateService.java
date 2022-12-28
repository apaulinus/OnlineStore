package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sss.onlinestore.model.State;
import com.sss.onlinestore.repository.StateRepository;

@Service
public class StateService {

	Logger LOG = LoggerFactory.getLogger(StateService.class);
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> findStates(){
		List<State> states = new ArrayList<>();
		stateRepository.findAll().forEach(state -> states.add(state));
		return states;
	}
	
	public Optional<State> findState(Long id){
		Assert.notNull(id, "State id can not be null.");
		
		return stateRepository.findById(id);
	}
	
	public void saveState(State state) {
		Assert.notNull(state, "State can not be null.");
		
		stateRepository.save(state);
	}
	
	public void updateState(Long id, State state) {
		Assert.notNull(id, "State id can not be null.");
		Assert.notNull(state, "State can not be null.");
		
		stateRepository.save(state);
	}
	
	@Transactional
	public void delete(Long id) {
		stateRepository.deleteById(id);
	}
}
