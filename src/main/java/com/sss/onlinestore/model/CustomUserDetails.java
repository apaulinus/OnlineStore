package com.sss.onlinestore.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends Customer implements UserDetails {

	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = -7144623330102891674L;

	public CustomUserDetails(final Customer customer) {
		// TODO Auto-generated constructor stub
		super(customer);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return getRoles()
		.stream()
		.map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
		.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
