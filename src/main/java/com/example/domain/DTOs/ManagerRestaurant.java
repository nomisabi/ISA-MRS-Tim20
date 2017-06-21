package com.example.domain.DTOs;

import com.example.domain.Manager;
import com.example.domain.Restaurant;

public class ManagerRestaurant {
	private Restaurant r;
	private Manager m;
	
	public ManagerRestaurant(){
		
	}
	
	public ManagerRestaurant(Restaurant r, Manager m) {
		super();
		this.r = r;
		this.m = m;
	}

	public Restaurant getR() {
		return r;
	}
	public void setR(Restaurant r) {
		this.r = r;
	}
	public Manager getM() {
		return m;
	}
	public void setM(Manager m) {
		this.m = m;
	}

	@Override
	public String toString() {
		return "ManagerRestaurant [r=" + r + ", m=" + m + "]";
	}

	
	
}
