package com.sss.onlinestore.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomerPurchaseHistoryResponse extends RestResponse{

	private Long id; 
	private String firstName; 
	private String lastName; 
	private String purchaseItem; 
	private LocalDateTime purchaseDate;
	
	
	public CustomerPurchaseHistoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerPurchaseHistoryResponse(String firstName, String lastName, String purchaseItem, LocalDateTime purchaseDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.purchaseItem = purchaseItem;
		this.purchaseDate = purchaseDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPurchaseItem() {
		return purchaseItem;
	}
	public void setPurchaseItem(String purchaseItem) {
		this.purchaseItem = purchaseItem;
	}
	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
