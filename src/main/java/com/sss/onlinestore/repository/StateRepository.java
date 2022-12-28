package com.sss.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sss.onlinestore.model.State;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {

}
