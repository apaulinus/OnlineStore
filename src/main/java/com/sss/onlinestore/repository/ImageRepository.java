package com.sss.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sss.onlinestore.model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

}
