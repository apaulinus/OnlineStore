package com.sss.onlinestore.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.onlinestore.admin.model.Users;
import com.sss.onlinestore.admin.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	public List<Users> findUsers() {
		return (List<Users>) usersRepository.findAll();
	}

	public Optional<Users> findUser(Long id) {
		return usersRepository.findById(id);
	}

	public Users saveUser(Users users) {
		return usersRepository.save(users);
	}

	public Users updateUser(Long id, Users users) {
		return usersRepository.save(users);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

}
