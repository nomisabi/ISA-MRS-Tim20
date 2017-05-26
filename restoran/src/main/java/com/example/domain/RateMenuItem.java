package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RateMenuItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Guest guest;
	@ManyToOne(fetch = FetchType.EAGER)
	private MenuItem menuItem;
	@ManyToOne(fetch = FetchType.EAGER)
	private Reservation reservation;
	@Column
	private int rate;

	public RateMenuItem() {

	}

	public RateMenuItem(Guest guest, MenuItem menuItem, Reservation reservation, int rate) {
		super();
		this.guest = guest;
		this.menuItem = menuItem;
		this.reservation = reservation;
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "RateMenuItem [guest=" + guest + ", menuItem=" + menuItem + ", reservation=" + reservation + ", rate="
				+ rate + "]";
	}

}
