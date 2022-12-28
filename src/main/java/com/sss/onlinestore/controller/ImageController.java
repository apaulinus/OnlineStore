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

import com.sss.onlinestore.model.Image;
import com.sss.onlinestore.service.ImageService;

@RestController
public class ImageController {

	Logger LOG = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/api/images")
	public List<Image> getImages(){
		return imageService.findImages();
	}
	
	@RequestMapping(value="/api/images/{id}")
	public Optional<Image> getImage(@PathVariable Long id) {
		Assert.notNull(id, "image id can not be null.");
		
		return imageService.findImage(id);
	}
	
	@RequestMapping(value="/api/images", method=RequestMethod.POST)
	public void addImage(@RequestBody Image image) {
		Assert.notNull(image, "image can not be null.");
		
		imageService.saveImage(image);
	}
	
	@RequestMapping(value="/api/images/{id}", method=RequestMethod.PUT)
	public void updateImage(@PathVariable Long id, @RequestBody Image image) {
		Assert.notNull(id, "image id can not be null.");
		Assert.notNull(image, "image can not be null.");
		
		
		imageService.updateImage(id, image);
	}
	
	@RequestMapping(value="/api/images/id", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		Assert.notNull(id, "image id can not be null.");
		
		imageService.delete(id);
	}
}
