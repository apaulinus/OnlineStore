package com.sss.onlinestore.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.sss.onlinestore.model.Category;
import com.sss.onlinestore.repository.CategoryRepository;

@Service
public class CategoryService {

	Logger LOG = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/*
	 * Find all Categories
	 * return
	 */
	public List<Category> findCategories(){
		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(category -> categories.add(category));
		return categories;
	}
	
	/*
	 * Find a Category using the id
	 * return
	 */
	public Optional<Category> findCategory(Long id){
		return categoryRepository.findById(id);
	}
	
	/*
	 * Save a category.
	 */
	@Transactional
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	/*
	 * Update a Category giving the id
	 * return
	 */
	@Transactional
	public Category updateCategory(Long id, Category category) {
		Optional<Category> categoryOpt = categoryRepository.findById(id);
		
		if (categoryOpt.isPresent()) {
			return categoryRepository.save(category);
		}
		return null;
	}
	
	/*
	 * Update some fields of a Category
	 * return
	 */
	@Transactional
	public Category updateCategoryByFields(Long id, Map<String, String> fields) {
		// TODO Auto-generated method stub
		
		Optional<Category> categoryOpt =  categoryRepository.findById(id);
		
		if(categoryOpt.isPresent()) {
			Category category = categoryOpt.get();
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Category.class, key);
				if (field != null) {
					field.setAccessible(true);
					ReflectionUtils.setField(field, category, value);
				}
			});
			
			return categoryRepository.save(category);
		}
		return new Category();
	}

	/*
	 * Delete a category
	 */
	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}

}
