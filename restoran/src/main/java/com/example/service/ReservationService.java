package com.example.service;

import java.util.Collection;

import com.example.domain.DrinkMenuItemReservation;
import com.example.domain.GuestReservation;
import com.example.domain.MenuItemReservation;
import com.example.domain.Reservation;
import com.example.domain.TableReservation;

public interface ReservationService {

	Reservation getReservation(Long id);

	Collection<Reservation> getAllReservations();

	Reservation createReservation(Reservation reservation);

	GuestReservation saveGuestReservation(GuestReservation guestReservation);

	Collection<Reservation> getAllReservationOfRestaurant(Long idRestaurant);

	Collection<TableReservation> getAllReservationOfRestaurantInTime(Long id, String dateStart, String dateEnd);

	Collection<Reservation> getVisitedRestaurant(Long id);

	TableReservation saveTable(TableReservation tableReservation);

	void setReservation(Long id, Reservation reservation);

	MenuItemReservation saveMenuItem(MenuItemReservation menuItemReservation);

	DrinkMenuItemReservation saveDrinkMenuItem(DrinkMenuItemReservation drinkMenuItemReservation);

}
