package com.example.domain.DTOs;

import com.example.domain.Menu;
import com.example.domain.Restaurant;

public class MenuRestaurant {
	private Menu m;
	private Restaurant r;
	
	public MenuRestaurant(){
	}

	public Menu getM() {
		return m;
	}

	public void setM(Menu m) {
		this.m = m;
	}

	public Restaurant getR() {
		return r;
	}

	public void setR(Restaurant r) {
		this.r = r;
	}

	public MenuRestaurant(Menu m, Restaurant r) {
		super();
		this.m = m;
		this.r = r;
	}
	
	
}
