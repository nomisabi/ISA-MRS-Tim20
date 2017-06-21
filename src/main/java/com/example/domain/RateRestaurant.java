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
public class RateRestaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Guest guest;
	@ManyToOne(fetch = FetchType.EAGER)
	private Restaurant restaurant;
	@ManyToOne(fetch = FetchType.EAGER)
	private Reservation reservation;
	@Column
	private int rate;
	@Column
	private int rateFood;

	public RateRestaurant() {
	}

	public RateRestaurant(Guest guest, Restaurant restaurant, Reservation reservation, int rate, int rateFood) {
		super();
		this.guest = guest;
		this.restaurant = restaurant;
		this.reservation = reservation;
		this.rate = rate;
		this.rateFood = rateFood;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

	public int getRateFood() {
		return rateFood;
	}

	public void setRateFood(int rateFood) {
		this.rateFood = rateFood;
	}

	@Override
	public String toString() {
		return "RateRestaurant [guest=" + guest + ", restaurant=" + restaurant + ", reservation=" + reservation
				+ ", rate=" + rate + "]";
	}

}
