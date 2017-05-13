package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	private Restaurant restaurant;

	// @ManyToOne(optional = false)
	// private TableOfRestaurant table;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
	private Set<GuestReservation> guestReservations;

	@Column
	private String startTime;

	@Column
	private String endTime;

	public Reservation() {
	}

	public Reservation(Restaurant restaurant, String startTime, String endTime) {
		super();
		this.restaurant = restaurant;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Reservation [restaurant=" + restaurant + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
