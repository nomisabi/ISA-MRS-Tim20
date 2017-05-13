package com.example.service;

import java.util.Collection;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.domain.DrinkMenuItemReservation;
import com.example.domain.GuestReservation;
import com.example.domain.MenuItemReservation;
import com.example.domain.Reservation;
import com.example.domain.TableReservation;
import com.example.respository.DrinkMenuItemReservationRepository;
import com.example.respository.GuestReservationRepository;
import com.example.respository.MenuItemReservationRepository;
import com.example.respository.ReservationRepository;
import com.example.respository.TableReservationRepository;

@Service
@Transactional(readOnly = true)
public class ReservationServiceImp implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	GuestReservationRepository guestReservationRepository;
	@Autowired
	TableReservationRepository tableReservationRepository;
	@Autowired
	MenuItemReservationRepository menuReservationRepository;
	@Autowired
	DrinkMenuItemReservationRepository drinkMenuReservationRepository;

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
	public Reservation createReservation(Reservation reservation) {
		Assert.notNull(reservation, "Reservation could not be null.");
		// Collection<Reservation> reservations =
		// reservationRepository.get(reservation.getRestaurant().getId(),
		// reservation.getTable().getId(), reservation.getStartTime(),
		// reservation.getEndTime());
		// if (!reservations.isEmpty()) {
		// return null;
		// }
		return reservationRepository.save(reservation);
	}

	@Override
	@Transactional(readOnly = false)
	@Lock(LockModeType.PESSIMISTIC_READ)
	public TableReservation saveTable(TableReservation tableReservation) {
		Assert.notNull(tableReservation, "TableReservation could not be null.");
		Collection<TableReservation> tableReservations = tableReservationRepository.get(
				tableReservation.getTable().getId(), tableReservation.getStartTime(), tableReservation.getEndTime());
		if (!tableReservations.isEmpty()) {
			return null;
		}
		return tableReservationRepository.save(tableReservation);
	}

	@Override
	@Transactional(readOnly = false)
	public GuestReservation saveGuestReservation(GuestReservation guestReservation) {
		Assert.notNull(guestReservation, "Reservation could not be null.");
		return guestReservationRepository.save(guestReservation);
	}

	@Override
	public Collection<Reservation> getAllReservationOfRestaurant(Long idRestaurant) {
		return reservationRepository.getAllReservationOfRestaurant(idRestaurant);
	}

	@Override
	public Collection<TableReservation> getAllReservationOfRestaurantInTime(Long id, String dateStart, String dateEnd) {
		return reservationRepository.getAllReservationTable(id, dateStart, dateEnd);
	}

	@Override
	public Collection<Reservation> getVisitedRestaurant(Long id) {
		return reservationRepository.getVisitedRestaurant(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void setReservation(Long id, Reservation reservation) {
		tableReservationRepository.setReservation(id, reservation);
	}

	@Override
	@Transactional(readOnly = false)
	public MenuItemReservation saveMenuItem(MenuItemReservation menuItemReservation) {
		return menuReservationRepository.save(menuItemReservation);
	}

	@Override
	@Transactional(readOnly = false)
	public DrinkMenuItemReservation saveDrinkMenuItem(DrinkMenuItemReservation drinkMenuItemReservation) {
		return drinkMenuReservationRepository.save(drinkMenuItemReservation);
	}
}
