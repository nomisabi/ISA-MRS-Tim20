package com.example.domain.DTOs;

import java.util.Collection;

import com.example.domain.DrinkMenuItemReservation;
import com.example.domain.Guest;
import com.example.domain.MenuItemReservation;
import com.example.domain.Reservation;
import com.example.domain.TableOfRestaurant;

public class ReservationDetails {
	private Reservation reservation;
	private Long guestReservationId;
	private Collection<TableOfRestaurant> tables;
	private Collection<Guest> guests;
	private Collection<MenuItemReservation> menuItems;
	private Collection<DrinkMenuItemReservation> drinkMenuItems;
	private boolean flag;
	private boolean flagRate;
	private int rateRestaurant;
	private int rateFood;
	private boolean prepared;

	public ReservationDetails() {

	}

	public ReservationDetails(Collection<MenuItemReservation> menuItems,
			Collection<DrinkMenuItemReservation> drinkMenuItems) {
		super();
		this.menuItems = menuItems;
		this.drinkMenuItems = drinkMenuItems;
	}

	public ReservationDetails(Reservation reservation, Long guestReservationId, Collection<TableOfRestaurant> tables,
			Collection<Guest> guests, Collection<MenuItemReservation> menuItems,
			Collection<DrinkMenuItemReservation> drinkMenuItems, boolean flag, boolean flagRate, int rateRestaurant,
			int rateFood) {
		super();
		this.reservation = reservation;
		this.guestReservationId = guestReservationId;
		this.tables = tables;
		this.guests = guests;
		this.menuItems = menuItems;
		this.drinkMenuItems = drinkMenuItems;
		this.flag = flag;
		this.flagRate = flagRate;
		this.rateRestaurant = rateRestaurant;
		this.rateFood = rateFood;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlagRate() {
		return flagRate;
	}

	public void setFlagRate(boolean flagRate) {
		this.flagRate = flagRate;
	}

	public int getRateRestaurant() {
		return rateRestaurant;
	}

	public void setRateRestaurant(int rateRestaurant) {
		this.rateRestaurant = rateRestaurant;
	}

	public int getRateFood() {
		return rateFood;
	}

	public void setRateFood(int rateFood) {
		this.rateFood = rateFood;
	}

	public boolean isPrepared() {
		return prepared;
	}

	public void setPrepared(boolean prepared) {
		this.prepared = prepared;
	}

}
