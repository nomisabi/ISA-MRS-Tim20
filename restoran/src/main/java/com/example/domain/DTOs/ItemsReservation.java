package com.example.domain.DTOs;

import java.util.Collection;

import com.example.domain.DrinkMenuItemReservation;
import com.example.domain.Guest;
import com.example.domain.MenuItemReservation;
import com.example.domain.Reservation;

public class ItemsReservation {
	private Collection<MenuItemReservation> menuItems;
	private Collection<DrinkMenuItemReservation> drinkMenuItems;
	private Guest guest;
	private Reservation reservation;

	public ItemsReservation() {
	}

	public ItemsReservation(Collection<MenuItemReservation> menuItems,
			Collection<DrinkMenuItemReservation> drinkMenuItems) {
		super();
		this.menuItems = menuItems;
		this.drinkMenuItems = drinkMenuItems;
	}

	public Collection<MenuItemReservation> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Collection<MenuItemReservation> menuItems) {
		this.menuItems = menuItems;
	}

	public Collection<DrinkMenuItemReservation> getDrinkMenuItems() {
		return drinkMenuItems;
	}

	public void setDrinkMenuItems(Collection<DrinkMenuItemReservation> drinkMenuItems) {
		this.drinkMenuItems = drinkMenuItems;
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
		return "ItemsReservation [menuItems=" + menuItems + ", drinkMenuItems=" + drinkMenuItems + "]";
	}

}
