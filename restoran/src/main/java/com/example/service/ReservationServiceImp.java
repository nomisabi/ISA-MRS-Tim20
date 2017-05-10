<<<<<<< HEAD
package com.example.service;

import java.util.Collection;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.domain.Reservation;
import com.example.respository.ReservationRepository;

@Service
@Transactional(readOnly = true)
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
	@Transactional(readOnly = false)
	@Lock(LockModeType.PESSIMISTIC_READ)
	public Reservation createReservation(Reservation reservation) {
		Assert.notNull(reservation, "Reservation could not be null.");
		Collection<Reservation> reservations = reservationRepository.get(reservation.getRestaurant().getId(),
				reservation.getTable().getId(), reservation.getStartTime(), reservation.getEndTime());
		if (!reservations.isEmpty()) {
			return null;
		}
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
=======
package com.example.service;

import java.util.Collection;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.domain.Reservation;
import com.example.respository.ReservationRepository;

@Service
@Transactional(readOnly = true)
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
	@Transactional(readOnly = false)
	@Lock(LockModeType.PESSIMISTIC_READ)
	public Reservation createReservation(Reservation reservation) {
		Assert.notNull(reservation, "Reservation could not be null.");
		Collection<Reservation> reservations = reservationRepository.get(reservation.getRestaurant().getId(),
				reservation.getTable().getId(), reservation.getStartTime(), reservation.getEndTime());
		if (!reservations.isEmpty()) {
			return null;
		}
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
>>>>>>> branch 'master' of https://github.com/nevenaDj/ISA-MRS-Tim20
