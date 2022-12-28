package com.sss.onlinestore.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sss.onlinestore.repository.CustomerRepository;
import com.sss.onlinestore.security.JwtAuthenticationEntryPoint;
import com.sss.onlinestore.security.JwtAuthenticationProvider;
import com.sss.onlinestore.security.JwtAuthenticationTokenFilter;
import com.sss.onlinestore.security.JwtSuccessHandler;
import com.sss.onlinestore.service.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private static final String secureUrl = "**/api/secured/rest/**";
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationFilter() {
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter(secureUrl);
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		return filter;
	};
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("**/admin/**").hasRole("ADMIN")
		.antMatchers("**/user/**").hasAnyRole("USER", "ADMIN")
		.antMatchers("/", 
				"/api/rest/logins", 
				"/api/rest/registers", 
				"/api/rest/states", 
				"/api/rest/customers", 
				"/api/rest/categories", 
				"/api/rest/states", 
				secureUrl)
		.permitAll().anyRequest()
		//.authenticated()
		.fullyAuthenticated()
		.and()
		.formLogin().loginPage("http://localhost:4200/login").permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().authenticationEntryPoint(entryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http.headers().cacheControl();
	}
	
	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return charSequence.toString();
			}
			
			@Override
			public boolean matches (CharSequence charSequence, String s) {
				return true;
			}
		};
	}
}
