package com.adminweb.model.domain;

import javax.validation.constraints.NotNull;

public class AdminUser {
	
	@NotNull
	private String email;

	@NotNull
	private String password;
	
	public String getEmail() {
		return email;
	}
	public AdminUser setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public AdminUser setPassword(String password) {
		this.password = password;
		return this;
	}
	
	
}
