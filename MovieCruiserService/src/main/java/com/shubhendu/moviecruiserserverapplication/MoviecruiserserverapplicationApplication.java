package com.shubhendu.moviecruiserserverapplication;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.shubhendu.moviecruiserserverapplication.filter.JwtFilter;

@SpringBootApplication
public class MoviecruiserserverapplicationApplication {

	
	@Bean
	public FilterRegistrationBean jwtFilter(){
		final FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		
		return registrationBean;
		}
	public static void main(String[] args) {
		SpringApplication.run(MoviecruiserserverapplicationApplication.class, args);
	}

}
