package com.sss.onlinestore.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sss.onlinestore.filter.UniqueIdFilter;

import lombok.Data;

@Configuration
@Data
public class UniqueIdFilterConfiguration {
	
	public static final String DEFAULT_HEADER_TOKEN = "corelationId";
	public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "correlationId";
	
	private String responseHeader = DEFAULT_HEADER_TOKEN;
	private String mdcKey = DEFAULT_MDC_UUID_TOKEN_KEY;
	private String requestHeader = DEFAULT_HEADER_TOKEN;
	
	@Bean
	public FilterRegistrationBean<UniqueIdFilter> servletRegistrationBean() {
		final FilterRegistrationBean<UniqueIdFilter> registrationBean = new FilterRegistrationBean<>();
		final UniqueIdFilter log4jMDCFilter = new UniqueIdFilter(responseHeader, mdcKey, requestHeader);
		registrationBean.setFilter(log4jMDCFilter);
		registrationBean.setOrder(2);
		
		return registrationBean;
	}

}
