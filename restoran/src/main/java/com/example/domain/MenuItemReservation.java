package com.example.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MenuItemReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private MenuItem menuItem;
	@ManyToOne(fetch = FetchType.EAGER)
	private Guest guest;
	@ManyToOne(fetch = FetchType.EAGER)
	private Reservation reservation;

	public MenuItemReservation() {
	}

	public MenuItemReservation(MenuItem menuItem, Guest guest, Reservation reservation) {
		super();
		this.menuItem = menuItem;
		this.guest = guest;
		this.reservation = reservation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
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
		return "MenuItemReservation [menuItem=" + menuItem + ", guest=" + guest + ", reservation=" + reservation + "]";
	}

}
