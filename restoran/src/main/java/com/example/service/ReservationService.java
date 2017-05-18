package com.example.service;

import java.util.Collection;

import com.example.domain.DrinkMenuItem;
import com.example.domain.DrinkMenuItemReservation;
import com.example.domain.Guest;
import com.example.domain.GuestReservation;
import com.example.domain.MenuItem;
import com.example.domain.MenuItemReservation;
import com.example.domain.Reservation;
import com.example.domain.TableOfRestaurant;
import com.example.domain.TableReservation;

public interface ReservationService {

	Reservation getReservation(Long id);

	Collection<Reservation> getAllReservations();

	Reservation createReservation(Reservation reservation);

	TableReservation saveTable(TableReservation tableReservation);

	GuestReservation saveGuestReservation(GuestReservation guestReservation);

	MenuItemReservation saveMenuItem(MenuItemReservation menuItemReservation);

	DrinkMenuItemReservation saveDrinkMenuItem(DrinkMenuItemReservation drinkMenuItemReservation);

	void setReservation(Long id, Reservation reservation);

	void confirmReservation(Long guestReservationid);

	Collection<TableReservation> getAllReservationTable(Long idRestaurant, String dateStart, String dateEnd);

	Collection<TableOfRestaurant> getAllTableOfReservation(Long idReservation);

	Collection<MenuItem> getAllMenuItemReservation(Long idReservation, Long idGuest);

	Collection<DrinkMenuItem> getAllDrinkMenuItemReservation(Long idReservation, Long idGuest);

	Collection<Guest> getFriends(Long idReservation, Long idGuest);

	Collection<Reservation> getVisitedRestaurants(Long idGuest);

	Reservation getReservationOfGuest(Long idGuest);

	Long getGuestReservationId(Long idReservation, Long idGuest);

	Long getGuestReservationId(String token);

	Guest getGuestOfGuestReservation(Long id);

	Long getVerificationId(Long idGuestReservation);

	void deleteToken(Long id);

	void deleteGuestReservation(Long id);

	Collection<TableReservation> getbyTable(Long id_table);

}
