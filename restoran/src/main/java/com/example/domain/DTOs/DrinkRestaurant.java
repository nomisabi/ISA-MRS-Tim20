package com.example.domain.DTOs;

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.Menu;
import com.example.domain.Restaurant;

public class DrinkRestaurant {
	private DrinkMenu d;
	private Restaurant r;
	
	public DrinkRestaurant(){
	}

	public DrinkMenu getD() {
		return d;
	}

	public void setD(DrinkMenu d) {
		this.d = d;
	}

	public Restaurant getR() {
		return r;
	}

	public void setR(Restaurant r) {
		this.r = r;
	}

	public DrinkRestaurant(DrinkMenu d, Restaurant r) {
		super();
		this.d = d;
		this.r = r;
	}
}
