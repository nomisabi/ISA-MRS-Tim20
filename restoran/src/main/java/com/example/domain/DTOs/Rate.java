package com.example.domain.DTOs;

import com.example.domain.RateRestaurant;

public class Rate {
	private RateRestaurant rateRestaurant;

	public Rate() {

	}

	public RateRestaurant getRateRestaurant() {
		return rateRestaurant;
	}

	public void setRateRestaurant(RateRestaurant rateRestaurant) {
		this.rateRestaurant = rateRestaurant;
	}

	@Override
	public String toString() {
		return "Rate [rateRestaurant=" + rateRestaurant + "]";
	}

}
