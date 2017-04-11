package com.example.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Manager {
	@NotEmpty(message = "Email je obavezna.")
	private String email;
	@NotEmpty(message = "Lozinka je obavezna.")
	private String password;
	private String firstName;
	private String lastName;
	private Restaurant restaurant;
	
	public Manager(){
		
	}

	public Manager(String email, String password, String firstName, String lastName, Restaurant restaurant) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.restaurant = restaurant;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
