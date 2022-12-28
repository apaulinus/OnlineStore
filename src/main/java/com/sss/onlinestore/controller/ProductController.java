package com.sss.onlinestore.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sss.onlinestore.model.Product;
import com.sss.onlinestore.service.ProductService;

@RestController
public class ProductController {

	Logger LOG = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/api/products")
	public List<Product> getProducts(){
		return productService.findProducts();
	}
	
	@RequestMapping(value="/api/products/{id}")
	public Optional<Product> getProduct(@PathVariable Long id) {
		Assert.notNull(id, "Product id can not be null.");
		
		return productService.findProduct(id);
	}
	
	@RequestMapping(value="/api/products", method = RequestMethod.POST)
	public void addProduct(@RequestBody Product product) {
		Assert.notNull(product, "Product can not be null.");
		
		productService.saveProduct(product);
	}
	
	@RequestMapping(value="/api/products/{id}", method=RequestMethod.PUT)
	public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Assert.notNull(id, "Product id can not be null.");
		Assert.notNull(product, "Product can not be null.");
		
		productService.updateProduct(id, product);
	}
	
	@RequestMapping(value="/api/products/id", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		Assert.notNull(id, "Product id can not be null.");
		
		productService.delete(id);
	}
}
