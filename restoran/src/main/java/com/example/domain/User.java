package com.example.domain;

public class User {
	private String email;
	private String password;
	private TypeOfUser type;
	
	public User(String email, String password, TypeOfUser type) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public User() {

	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", type=" + type + "]";
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

	public TypeOfUser getType() {
		return type;
	}

	public void setType(TypeOfUser type) {
		this.type = type;
	}
	
	
	
}
