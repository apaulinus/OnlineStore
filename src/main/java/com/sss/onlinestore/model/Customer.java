package com.sss.onlinestore.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long titleId;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "user_name")
	private String userName;

	private String email;
	private String password;
	
	@OneToOne(optional = true, mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnore
    private CustomerDetail customerDetail;
	
	@OneToMany(targetEntity = PurchaseHistory.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_customer_id", referencedColumnName = "id")
	@JsonIgnore
    private List<PurchaseHistory> purchaseHistory = new ArrayList<>();
	
	@OneToMany(targetEntity = LoginHistory.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_customer_id", referencedColumnName = "id")
	@JsonIgnore
	@JsonBackReference
    private List<LoginHistory> loginHistory = new ArrayList<>();

	@OneToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
	@JoinColumn(name="fk_customer_id", referencedColumnName = "id")
	@JsonIgnore
	@JsonBackReference
    private Set<Role> roles = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Customer_Product",
		joinColumns = {
				@JoinColumn(name="customerId", referencedColumnName = "id")
		},
		inverseJoinColumns = {
				@JoinColumn(name="productId", referencedColumnName = "id")
		}
	)
	@JsonManagedReference
	private Set<Product> products = new HashSet<>();
	
	@ElementCollection
	private Map<String,String> contactAddress;
	
	@ElementCollection
	private Map<String,String> shippingAddress;
	
	public Customer() {
	}

	public Customer(Customer customer) {
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.email = customer.getEmail();
		this.id = customer.getId();
		this.userName = customer.getUserName();
		this.password = customer.getPassword();
	}

	public Customer(Long id, String password, String firstName, String lastName) {
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
