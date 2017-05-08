package com.example.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.domain.Reservation;
import com.example.respository.ReservationRepository;

@Service
public class ReservationServiceImp implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public Reservation getReservation(Long id) {
		return reservationRepository.findOne(id);
	}

	@Override
	public Collection<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation createReservation(Reservation reservation) {
		Assert.notNull(reservation, "Reservation could not be null.");
		return reservationRepository.save(reservation);
	}

	@Override
	public Collection<Reservation> getAllReservationOfRestaurant(Long idRestaurant) {
		return reservationRepository.getAllReservationOfRestaurant(idRestaurant);
	}

	@Override
	public Collection<Reservation> getAllReservationOfRestaurantInTime(Long id, String dateStart, String dateEnd) {
		return reservationRepository.getAllReservationOfRestaurantInTime(id, dateStart, dateEnd);
	}
}
