package com.example.domain.DTOs;

import java.io.Serializable;

import javax.persistence.Entity;

public class FoodRate implements Serializable{

	private String name;
	private double rate;
	
	public FoodRate(String name, double rate) {
		super();
		this.name = name;
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "FoodRate [name=" + name + ", rate=" + rate + "]";
	}
	
	
}
