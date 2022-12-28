package com.sss.onlinestore.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String description;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private LocalDate publicationDate;

	private double price;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Size size;
	
	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Customer> customers = new HashSet<>();

	public void setPublicationDate(String publicationDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		this.publicationDate = LocalDate.parse(publicationDate, formatter);
	}

}
