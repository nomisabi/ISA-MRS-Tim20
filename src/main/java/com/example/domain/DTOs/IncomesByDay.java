package com.example.domain.DTOs;

public class IncomesByDay {

	private String day;
	private double price;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public IncomesByDay(String day, double price) {
		super();
		this.day = day;
		this.price = price;
	}
	
	
	
}
