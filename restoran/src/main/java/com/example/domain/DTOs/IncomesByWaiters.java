package com.example.domain.DTOs;

public class IncomesByWaiters {

	private String firstName;
	private String lastName;
	private double price;
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public IncomesByWaiters(String firstName, String lastName, double price) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.price = price;
	}
	
	
}
