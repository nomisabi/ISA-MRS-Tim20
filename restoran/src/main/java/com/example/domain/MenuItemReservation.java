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
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
	@Column
	private boolean prepared;

	public MenuItemReservation() {
	}

	public MenuItemReservation(MenuItem menuItem, Guest guest, Reservation reservation) {
		super();
		this.menuItem = menuItem;
		this.guest = guest;
		this.reservation = reservation;
	}

	public MenuItemReservation(MenuItem menuItem, Guest guest, Reservation reservation, boolean prepared) {
		super();
		this.menuItem = menuItem;
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

	public boolean isPrepared() {
		return prepared;
	}

	public void setPrepared(boolean prepared) {
		this.prepared = prepared;
	}

	@Override
	public String toString() {
		return "MenuItemReservation [menuItem=" + menuItem + ", guest=" + guest + ", reservation=" + reservation + "]";
	}

}
