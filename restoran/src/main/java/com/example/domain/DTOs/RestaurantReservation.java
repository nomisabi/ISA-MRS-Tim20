package com.example.domain.DTOs;

import java.util.Date;

import com.example.domain.Guest;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;

public class RestaurantReservation {
	private Restaurant restaurant;
	private TableOfRestaurant table;
	private Guest guest;
	private Date dateAndTime;
	private int duration;

	public RestaurantReservation() {

	}

	public RestaurantReservation(Restaurant restaurant, TableOfRestaurant table, Guest guest, Date dateAndTime,
			int duration) {
		super();
		this.restaurant = restaurant;
		this.table = table;
		this.guest = guest;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public TableOfRestaurant getTable() {
		return table;
	}

	public void setTable(TableOfRestaurant table) {
		this.table = table;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "RestaurantReservation [restaurant=" + restaurant + ", table=" + table + ", guest=" + guest
				+ ", dateAndTime=" + dateAndTime + ", duration=" + duration + "]";
	}

}
