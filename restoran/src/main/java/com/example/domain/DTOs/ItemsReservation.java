package com.example.domain.DTOs;

import java.util.Collection;

import com.example.domain.DrinkMenuItem;
import com.example.domain.Guest;
import com.example.domain.MenuItem;
import com.example.domain.Reservation;

public class ItemsReservation {
	private Collection<MenuItem> menuItems;
	private Collection<DrinkMenuItem> drinkMenuItems;
	private Reservation reservation;
	private Guest guest;

	public ItemsReservation() {
	}

	public ItemsReservation(Collection<MenuItem> menuItems, Collection<DrinkMenuItem> drinkMenuItems,
			Reservation reservation, Guest guest) {
		super();
		this.menuItems = menuItems;
		this.drinkMenuItems = drinkMenuItems;
		this.reservation = reservation;
		this.guest = guest;
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

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "ItemsReservation [menuItems=" + menuItems + ", drinkMenuItems=" + drinkMenuItems + ", reservation="
				+ reservation + ", guest=" + guest + "]";
	}

}
