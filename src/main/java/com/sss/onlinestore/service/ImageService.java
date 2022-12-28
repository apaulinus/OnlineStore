package com.sss.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sss.onlinestore.model.Image;
import com.sss.onlinestore.repository.ImageRepository;

@Service
public class ImageService {

	Logger LOG = LoggerFactory.getLogger(ImageService.class);
	
	@Autowired
	private ImageRepository imageRepository;
	
	public List<Image> findImages(){
		List<Image> images = new ArrayList<>();
		imageRepository.findAll().forEach(image -> images.add(image));
		return images;
	}
	
	public Optional<Image> findImage(Long id){
		Assert.notNull(id, "image id can not be null.");
		
		return imageRepository.findById(id);
	}
	
	public void saveImage(Image image) {
		Assert.notNull(image, "image can not be null.");
		
		imageRepository.save(image);
	}
	
	public void updateImage(Long id, Image image) {
		Assert.notNull(image, "image can not be null.");
		
		imageRepository.save(image);
	}
	
	public void delete(Long id) {
		Assert.notNull(id, "image id can not be null.");
		
		imageRepository.deleteById(id);
	}
}
