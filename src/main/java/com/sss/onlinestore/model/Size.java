package com.sss.onlinestore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Size {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String size;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_size_id")
	private List<Product> products = new ArrayList<>();

}
