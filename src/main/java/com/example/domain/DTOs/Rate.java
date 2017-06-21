package com.example.domain.DTOs;

import com.example.domain.Guest;
import com.example.domain.Reservation;


public class Rate {
	private Guest guest;
	private Reservation reservation;
	private int rateMenu;
	private int rateRestaurant;

	public Rate() {

	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	public int getRateMenu() {
		return rateMenu;
	}

	public void setRateMenu(int rateMenu) {
		this.rateMenu = rateMenu;
	}

	public int getRateRestaurant() {
		return rateRestaurant;
	}

	public void setRateRestaurant(int rateRestaurant) {
		this.rateRestaurant = rateRestaurant;
	}

	
	
	

}
