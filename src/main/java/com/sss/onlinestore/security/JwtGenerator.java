package com.sss.onlinestore.security;

import org.springframework.stereotype.Component;

import com.sss.onlinestore.security.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	public String generate(JwtUser jwtUser) {
		
		Claims claims = Jwts.claims()
				.setSubject(jwtUser.getUsername());
		claims.put("userId", String.valueOf(jwtUser.getId()));
		claims.put("role", jwtUser.getRole());
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, "youtube")
				.compact();
	}
}
