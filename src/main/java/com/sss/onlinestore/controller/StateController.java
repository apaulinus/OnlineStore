package com.sss.onlinestore.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sss.onlinestore.model.State;
import com.sss.onlinestore.service.StateService;

@RestController
@RequestMapping(value="/api/rest/states")
public class StateController {

	Logger LOG = LoggerFactory.getLogger(StateController.class);

	@Autowired
	private StateService stateService;
	
	@GetMapping()
	public ResponseEntity<List<State>> getStates(){
		List<State> states = stateService.findStates();
		return  ResponseEntity.ok(states);
	}
	
	@GetMapping(value="/{id}")
	public Optional<State> getState(@PathVariable Long id) {
		Assert.notNull(id, "State id can not be null.");
		
		return stateService.findState(id);
	}
	
	@PostMapping()
	public void addState(@RequestBody State state) {
		Assert.notNull(state, "State can not be null.");
		
		stateService.saveState(state);
	}
	@PutMapping(value="/{id}")
	public void updateState(@PathVariable Long id, @RequestBody State state) {
		Assert.notNull(id, "State id can not be null.");
		Assert.notNull(state, "State can not be null.");
		
		stateService.updateState(id, state);
	}
	
	@DeleteMapping(value="/id")
	public void delete(@PathVariable Long id) {
		Assert.notNull(id, "State id can not be null.");
		
		stateService.delete(id);
	}
}
