package com.example.domain.DTOs;

import java.util.Collection;

import com.example.domain.DrinkMenuItemReservation;
import com.example.domain.MenuItemReservation;

public class ItemsReservation {
	private Collection<MenuItemReservation> menuItems;
	private Collection<DrinkMenuItemReservation> drinkMenuItems;

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

	@Override
	public String toString() {
		return "ItemsReservation [menuItems=" + menuItems + ", drinkMenuItems=" + drinkMenuItems + "]";
	}

}
