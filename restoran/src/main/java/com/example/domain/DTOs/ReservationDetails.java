package com.example.domain.DTOs;

import java.util.Collection;

import com.example.domain.DrinkMenuItem;
import com.example.domain.Guest;
import com.example.domain.MenuItem;
import com.example.domain.Reservation;
import com.example.domain.TableOfRestaurant;

public class ReservationDetails {
	private Reservation reservation;
	private Long guestReservationId;
	private Collection<TableOfRestaurant> tables;
	private Collection<Guest> guests;
	private Collection<MenuItem> menuItems;
	private Collection<DrinkMenuItem> drinkMenuItems;
	private boolean flag;

	public ReservationDetails() {

	}

	public ReservationDetails(Reservation reservation, Long guestReservationId, Collection<TableOfRestaurant> tables,
			Collection<Guest> guests, Collection<MenuItem> menuItems, Collection<DrinkMenuItem> drinkMenuItems,
			boolean flag) {
		super();
		this.reservation = reservation;
		this.guestReservationId = guestReservationId;
		this.tables = tables;
		this.guests = guests;
		this.menuItems = menuItems;
		this.drinkMenuItems = drinkMenuItems;
		this.flag = flag;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Long getGuestReservationId() {
		return guestReservationId;
	}

	public void setGuestReservationId(Long guestReservationId) {
		this.guestReservationId = guestReservationId;
	}

	public Collection<TableOfRestaurant> getTables() {
		return tables;
	}

	public void setTables(Collection<TableOfRestaurant> tables) {
		this.tables = tables;
	}

	public Collection<Guest> getGuests() {
		return guests;
	}

	public void setGuests(Collection<Guest> guests) {
		this.guests = guests;
	}

	public Collection<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Collection<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public Collection<DrinkMenuItem> getDrinkMenuItems() {
		return drinkMenuItems;
	}

	public void setDrinkMenuItems(Collection<DrinkMenuItem> drinkMenuItems) {
		this.drinkMenuItems = drinkMenuItems;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
