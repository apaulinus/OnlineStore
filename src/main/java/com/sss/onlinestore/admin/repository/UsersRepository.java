package com.sss.onlinestore.admin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sss.onlinestore.admin.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

}
