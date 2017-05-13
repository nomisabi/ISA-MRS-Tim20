package com.example.domain.DTOs;

import java.util.Collection;
import java.util.Date;

import com.example.domain.Guest;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;

public class RestaurantReservation {
	private Restaurant restaurant;
	private Collection<TableOfRestaurant> tables;
	private Guest guest;
	private Date dateAndTime;
	private int duration;

	public RestaurantReservation() {

	}

	public RestaurantReservation(Restaurant restaurant, Collection<TableOfRestaurant> tables, Guest guest,
			Date dateAndTime, int duration) {
		super();
		this.restaurant = restaurant;
		this.tables = tables;
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

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Collection<TableOfRestaurant> getTables() {
		return tables;
	}

	public void setTables(Collection<TableOfRestaurant> tables) {
		this.tables = tables;
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
		return "RestaurantReservation [restaurant=" + restaurant + ", tables=" + tables + ", guest=" + guest
				+ ", dateAndTime=" + dateAndTime + ", duration=" + duration + "]";
	}

}
