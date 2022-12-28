package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sss.onlinestore.model.Product;
import com.sss.onlinestore.repository.ProductRepository;

@Service
public class ProductService {
	
	Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findProducts(){
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(product -> products.add(product));
		return products;
	}
	
	public Optional<Product> findProduct(Long id){
		Assert.notNull(id, "Product id can not be null.");
		
		return productRepository.findById(id);
	}
	
	public void saveProduct(Product product) {
		Assert.notNull(product, "Product can not be null.");
		productRepository.save(product);
	}
	
	public void updateProduct(Long id, Product product) {
		Assert.notNull(id, "Product id can not be null.");
		Assert.notNull(product, "Product can not be null.");
		
		productRepository.save(product);
	}
	
	public void delete(Long id) {
		Assert.notNull(id, "Product id can not be null.");
		
		productRepository.deleteById(id);
	}
}
