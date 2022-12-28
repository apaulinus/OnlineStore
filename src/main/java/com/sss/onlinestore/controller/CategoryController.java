package com.sss.onlinestore.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sss.onlinestore.model.Category;
import com.sss.onlinestore.service.CategoryService;

@RestController
@RequestMapping(value="/api/rest/categories")
public class CategoryController {

	Logger LOG = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Category> getCategories(){
		return categoryService.findCategories();
	}
	
	@GetMapping(value="/{id}")
	public Category getCategory(@PathVariable Long id) {
		Assert.notNull(id, "Category id can not be null.");
		
		Optional<Category> categoryOpt = categoryService.findCategory(id);
		
		if(categoryOpt.isPresent()) {
			return categoryOpt.get();
		}
		return new Category();
	}
	
	@PostMapping
	public void addCategories(@RequestBody Category category) {
		Assert.notNull(category, "Category can not be null.");
		
		categoryService.saveCategory(category);
	}
	
	@PostMapping(value="/{id}")
	public void updateCategory(@PathVariable Long id, @RequestBody Category category) {
		Assert.notNull(id, "Category id can not be null.");
		Assert.notNull(category, "Category can not be null.");
		
		categoryService.updateCategory(id, category);
	}
	
	@PatchMapping(value="/{id}")
	public void updateCategoryByFields(@PathVariable Long id, @RequestBody Map<String, String> fields) {
		Assert.notNull(id, "id can not be null.");
		Assert.notNull(fields, "fields can not be null.");
		
		categoryService.updateCategoryByFields(id, fields);
	}
	
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable Long id) {
		Assert.notNull(id, "Category id can not be null.");
		
		categoryService.delete(id);
	}
}
