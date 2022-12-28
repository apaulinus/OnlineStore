package com.sss.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sss.onlinestore.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	public Category findByProductsId(Long id);
}
