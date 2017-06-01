package com.example.domain.DTOs;

import com.example.validator.EmailAnnotation;

public class GuestRegister {
	@EmailAnnotation
	private String email;
	private String password;
	private String password2;
	private String firstName;
	private String lastName;
	private String oldPassword;
	private Long id;

	public GuestRegister() {
	}

	public GuestRegister(String email, String password, String password2, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.password2 = password2;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GuestRegister [email=" + email + ", password=" + password + ", password2=" + password2 + ", firstName="
				+ firstName + ", lastName=" + lastName + ", oldPassword=" + oldPassword + ", id=" + id + "]";
	}

}
