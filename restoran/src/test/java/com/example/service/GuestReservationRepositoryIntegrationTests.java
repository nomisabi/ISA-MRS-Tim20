package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.example.respository.GuestRepository;
import com.example.respository.GuestReservationRepository;
import com.example.respository.ReservationRepository;
import com.example.respository.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestReservationRepositoryIntegrationTests {
	@Autowired
	GuestReservationRepository repository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	GuestRepository guestRepository;

	Reservation reservation;
	GuestReservation guestReservation;
	Guest guest;

	@Before
	public void setUp() {
		Restaurant restaurant = new Restaurant("proba", "proba");
		restaurant = restaurantRepository.save(restaurant);
		reservation = new Reservation(restaurant, "2017-05-08 15:00", "2017-05-08 17:00");
		reservation = reservationRepository.save(reservation);
		guest = new Guest("novi@gmail.com", "123456", "proba", "proba1");
		guest = guestRepository.save(guest);
		guestReservation = new GuestReservation(guest, reservation, false);
		repository.save(guestReservation);
	}

	@Test
	public void getGuestReservation() {
		GuestReservation guestReservationFind = repository.findOne(guestReservation.getId());
		assertNotNull(guestReservationFind);
	}

	@Test
	public void update() {
		repository.confirmReservation(guestReservation.getId());
		GuestReservation updated = repository.findOne(guestReservation.getId());
		assertEquals(true, updated.isAccepted());
	}

}
