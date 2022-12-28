package com.sss.onlinestore.admin.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sss.onlinestore.admin.model.Users;
import com.sss.onlinestore.admin.service.UsersService;
import com.sss.onlinestore.response.RestResponse;

@Controller
@RequestMapping(value="/api/secured/rest/admin/users")
public class UsersController {

	@Autowired
	UsersService usersService;
	
	@GetMapping()
	public List<Users> getUsers(){

		return usersService.findUsers();
	}
	
	@GetMapping(value="/id")
	public Optional<Users> getUser(@PathParam("id") Long id) {
		Assert.notNull(id, "Users id is required.");
		
		return usersService.findUser(id);
	}
	
	@PostMapping()
	public ResponseEntity<RestResponse> addUser(Users users) {
		usersService.saveUser(users);
		return null;
	}
	
	@PutMapping(value="/id")
	public void udateUser(@PathParam("id") Long id, @RequestBody Users users) {
		usersService.updateUser(id, users);
	}
	
	@DeleteMapping(value="/id")
	public void deleteUser(@PathParam("id") Long id) {
		usersService.deleteUser(id);
	}
}
