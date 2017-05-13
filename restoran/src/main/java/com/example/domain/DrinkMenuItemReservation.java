package com.example.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DrinkMenuItemReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private DrinkMenuItem drinkMenuItem;
	@ManyToOne(fetch = FetchType.EAGER)
	private Guest guest;
	@ManyToOne(fetch = FetchType.EAGER)
	private Reservation reservation;

	public DrinkMenuItemReservation() {

	}

	public DrinkMenuItemReservation(DrinkMenuItem drinkMenuItem, Guest guest, Reservation reservation) {
		super();
		this.drinkMenuItem = drinkMenuItem;
		this.guest = guest;
		this.reservation = reservation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DrinkMenuItem getDrinkMenuItem() {
		return drinkMenuItem;
	}

	public void setDrinkMenuItem(DrinkMenuItem drinkMenuItem) {
		this.drinkMenuItem = drinkMenuItem;
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

	@Override
	public String toString() {
		return "DrinkMenuItemReservation [drinkMenuItem=" + drinkMenuItem + ", guest=" + guest + ", reservation="
				+ reservation + "]";
	}

}
