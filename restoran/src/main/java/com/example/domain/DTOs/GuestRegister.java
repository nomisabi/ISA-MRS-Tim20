package com.example.domain.DTOs;

import com.example.validator.EmailAnnotation;

public class GuestRegister {
	@EmailAnnotation
	private String email;
	private String password;
	private String password2;

	public GuestRegister() {
	}

	public GuestRegister(String email, String password, String password2) {
		super();
		
		this.email = email;
		this.password = password;
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	@Override
	public String toString() {
		return "GuestRegister [email=" + email + ", password=" + password + ", password2=" + password2 + "]";
	}

}
