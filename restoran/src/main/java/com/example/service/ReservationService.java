package com.example.service;

import java.util.Collection;

import com.example.domain.Reservation;

public interface ReservationService {

	Reservation getReservation(Long id);

	Collection<Reservation> getAllReservations();

	Reservation createReservation(Reservation reservation);

	Collection<Reservation> getAllReservationOfRestaurant(Long idRestaurant);

	Collection<Reservation> getAllReservationOfRestaurantInTime(Long id, String dateStart, String dateEnd);

}
