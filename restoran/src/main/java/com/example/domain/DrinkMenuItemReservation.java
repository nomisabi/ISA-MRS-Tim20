package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
	@Column
	private int quantity;
	@Column
	private boolean prepared;

	public DrinkMenuItemReservation() {

	}

	public DrinkMenuItemReservation(DrinkMenuItem drinkMenuItem, Guest guest, Reservation reservation) {
		super();
		this.drinkMenuItem = drinkMenuItem;
		this.guest = guest;
		this.reservation = reservation;
		this.prepared = false;
		this.quantity = 0;
	}

	public DrinkMenuItemReservation(DrinkMenuItem drinkMenuItem, Guest guest, Reservation reservation,
			boolean prepared) {
		super();
		this.drinkMenuItem = drinkMenuItem;
		this.guest = guest;
		this.reservation = reservation;
		this.prepared = prepared;
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

	public boolean isPrepared() {
		return prepared;
	}

	public void setPrepared(boolean prepared) {
		this.prepared = prepared;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "DrinkMenuItemReservation [drinkMenuItem=" + drinkMenuItem + ", guest=" + guest + ", reservation="
				+ reservation + "]";
	}

}
