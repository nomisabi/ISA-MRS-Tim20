package com.example.domain.DTOs;

import com.example.domain.Employee;
import com.example.domain.Restaurant;

public class EmployeeRestaurant {
	private Restaurant r;
	private Employee e;
	
	public Restaurant getR() {
		return r;
	}
	
	public void setR(Restaurant r) {
		this.r = r;
	}
	
	public Employee getE() {
		return e;
	}
	
	public void setE(Employee e) {
		this.e = e;
	}
	
	public EmployeeRestaurant(Restaurant r, Employee e) {
		super();
		this.r = r;
		this.e = e;
	}
	
	public EmployeeRestaurant() {
		super();
	}
	
	
}
