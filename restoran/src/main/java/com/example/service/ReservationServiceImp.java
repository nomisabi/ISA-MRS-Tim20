package com.example.service;

import java.util.Collection;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.domain.DrinkMenuItemReservation;
import com.example.domain.Guest;
import com.example.domain.GuestReservation;
import com.example.domain.MenuItemReservation;
import com.example.domain.RateMenuItem;
import com.example.domain.RateRestaurant;
import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.domain.TableReservation;
import com.example.respository.DrinkMenuItemReservationRepository;
import com.example.respository.GuestReservationRepository;
import com.example.respository.MenuItemReservationRepository;
import com.example.respository.RateMenuItemRepository;
import com.example.respository.RateRestaurantRepository;
import com.example.respository.ReservationRepository;
import com.example.respository.TableOfRestaurantRepository;
import com.example.respository.TableReservationRepository;
import com.example.respository.VerificationTokenRepository;

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
	@Autowired
	VerificationTokenRepository verificationRepository;
	@Autowired
	TableOfRestaurantRepository tableOfReservationRepository;
	@Autowired
	RateRestaurantRepository rateRestaurantRepository;
	@Autowired
	RateMenuItemRepository rateMenuItemRepostory;

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
		return reservationRepository.save(reservation);
	}

	@Override
	@Transactional(readOnly = false)
	@Lock(LockModeType.PESSIMISTIC_READ)
	public TableReservation saveTable(TableReservation tableReservation) {
		Assert.notNull(tableReservation, "TableReservation could not be null.");
		if (tableOfReservationRepository.exists(tableReservation.getTable().getId())) {
			// dobavi sve rezervisane stolove u zadato vreme
			Collection<TableReservation> tableReservations = tableReservationRepository.get(
					tableReservation.getTable().getId(), tableReservation.getStartTime(),
					tableReservation.getEndTime());
			// ako ima rezervisanih stolova u ovo vreme vrati null
			if (!tableReservations.isEmpty()) {
				return null;
			}
			// ako nema rezervisanih stolova upisi rezervaciju stola
			return tableReservationRepository.save(tableReservation);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public GuestReservation saveGuestReservation(GuestReservation guestReservation) {
		Assert.notNull(guestReservation, "GuestReservation could not be null.");
		return guestReservationRepository.save(guestReservation);
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

	@Override
	@Transactional(readOnly = false)
	public void setReservation(Long id, Reservation reservation) {
		tableReservationRepository.setReservation(id, reservation);
	}

	@Override
	@Transactional(readOnly = false)
	public void confirmReservation(Long idGuestReservation) {
		guestReservationRepository.confirmReservation(idGuestReservation);
	}

	@Override
	public Collection<TableReservation> getAllReservationTable(Long idRestaurant, String dateStart, String dateEnd) {
		return reservationRepository.getAllReservationTable(idRestaurant, dateStart, dateEnd);
	}

	@Override
	public Collection<TableOfRestaurant> getAllTableOfReservation(Long idReservation) {
		return tableReservationRepository.getAllTableReservation(idReservation);

	}

	@Override
	public Collection<MenuItemReservation> getAllMenuItemReservation(Long idReservation, Long idGuest) {
		return menuReservationRepository.getMenuItems(idReservation, idGuest);
	}

	@Override
	public Collection<DrinkMenuItemReservation> getAllDrinkMenuItemReservation(Long idReservation, Long idGuest) {
		return drinkMenuReservationRepository.getMenuItems(idReservation, idGuest);

	}

	@Override
	public Collection<Guest> getFriends(Long idReservation, Long idGuest) {
		return reservationRepository.getGuestOfReservation(idReservation, idGuest);
	}

	@Override
	public Collection<Reservation> getVisitedRestaurants(Long idGuest) {
		return reservationRepository.getVisitedRestaurant(idGuest);
	}

	@Override
	public Reservation getReservationOfGuest(Long idGuest) {
		return reservationRepository.getReservationOfGuest(idGuest);
	}

	@Override
	public Long getGuestReservationId(Long idReservation, Long idGuest) {
		return guestReservationRepository.getGuestOfReservation(idReservation, idGuest);
	}

	@Override
	public Long getGuestReservationId(String token) {
		return verificationRepository.getGuestReservationId(token);
	}

	@Override
	public Guest getGuestOfGuestReservation(Long id) {
		return reservationRepository.getGuestOfGuestReservation(id);
	}

	@Override
	public Long getVerificationId(Long idGuestReservation) {
		return verificationRepository.getId(idGuestReservation);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteGuestReservation(Long id) {
		guestReservationRepository.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteToken(Long id) {
		verificationRepository.delete(id);
	}

	@Override
	public Collection<TableReservation> getbyTable(Long id_table) {
		// return null;
		return tableReservationRepository.getByTable(id_table);
	}

	@Override
	public Reservation getReservationId(Long guestReservationId) {
		return guestReservationRepository.getReservationId(guestReservationId);
	}

	@Override
	public Restaurant getRestaurant(Long reservationId) {
		return reservationRepository.getRestaurant(reservationId);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateMenuItem(Long id, int quantity) {
		menuReservationRepository.update(id, quantity);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateDrinkItem(Long id, int quantity) {
		drinkMenuReservationRepository.update(id, quantity);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteMenuItem(Long id) {
		menuReservationRepository.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteDrinkItem(Long id) {
		drinkMenuReservationRepository.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteTableReservation(Long id) {
		tableReservationRepository.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public RateRestaurant saveRateRestaurant(RateRestaurant rateRestaurant) {
		return rateRestaurantRepository.save(rateRestaurant);
	}

	@Override
	@Transactional(readOnly = false)
	public RateMenuItem saveRateMenuItem(RateMenuItem rateMenuItem) {
		return rateMenuItemRepostory.save(rateMenuItem);
	}

}
