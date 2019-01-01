package com.adminweb.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.adminweb.config.interceptors.LoginInterceptor;

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/bower_components/**/*")
		.addResourceLocations("classpath:static/bower_components/");

		registry.addResourceHandler("/js/**/*")
		.addResourceLocations("classpath:static/js/");

		registry.addResourceHandler("/css/**/*")
		.addResourceLocations("classpath:static/css/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()); // 어드민 페이지 로그인 인터셉터 추가
	}
	
	
}