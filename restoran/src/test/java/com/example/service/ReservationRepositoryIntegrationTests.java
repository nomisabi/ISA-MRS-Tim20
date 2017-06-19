package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Guest;
import com.example.domain.GuestReservation;
import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.respository.GuestRepository;
import com.example.respository.GuestReservationRepository;
import com.example.respository.ReservationRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.TableOfRestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryIntegrationTests {

	@Autowired
	ReservationRepository repository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	TableOfRestaurantRepository tableRepository;
	@Autowired
	GuestRepository guestRepository;
	@Autowired
	GuestReservationRepository guestReservationRepository;

	Reservation reservation;
	Restaurant restaurant;
	Guest guest;
	GuestReservation guestReservation;

	@Before
	public void setUp() {
		restaurant = new Restaurant("proba", "proba");
		restaurant = restaurantRepository.save(restaurant);
		TableOfRestaurant table = new TableOfRestaurant(1, 5, restaurant);
		table = tableRepository.save(table);
		reservation = new Reservation(restaurant, "2017-05-08 15:00", "2017-05-08 17:00");
		reservation = repository.save(reservation);
		guest = new Guest("novi@gmail.com", "123456", "proba", "proba1");
		guest = guestRepository.save(guest);
		guestReservation = new GuestReservation(guest, reservation, false);
		guestReservationRepository.save(guestReservation);
	}

	@Test
	public void get() {
		Reservation findReservation = repository.findOne(reservation.getId());
		assertNotNull(findReservation);
	}

	@Test
	public void getReservation() {
		Reservation findReservation = repository.findOne(reservation.getId());
		assertNotNull(findReservation);
	}

	@Test
	public void getRestaurat() {
		Restaurant findReservation = repository.getRestaurant(reservation.getId());
		assertEquals(restaurant.getId(), findReservation.getId());
		assertEquals(restaurant.getName(), findReservation.getName());
	}

	

	@Test
	public void getVisitedRestaurants() {
		Collection<Reservation> r = repository.getVisitedRestaurant(guest.getId());
		assertNotEquals(0, r.size());
	}

	@Test
	public void getReservationGuest() {
		Reservation r = repository.getReservationOfGuest(guestReservation.getId());
		assertEquals(r.getId(), reservation.getId());
	}

	@Test
	public void getGuestOfGuestReservation() {
		Guest g = repository.getGuestOfGuestReservation(guestReservation.getId());
		assertEquals(g.getId(), guest.getId());
	}

}
