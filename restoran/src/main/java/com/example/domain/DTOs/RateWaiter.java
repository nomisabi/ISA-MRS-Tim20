package com.example.domain.DTOs;

public class RateWaiter {

	private String firstName;
	private String lastName;
	private double rate;
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
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public RateWaiter(String firstName, String lastName, double rate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.rate = rate;
	}
	
	
}
