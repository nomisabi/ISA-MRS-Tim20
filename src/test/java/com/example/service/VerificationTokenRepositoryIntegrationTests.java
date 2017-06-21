package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

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
import com.example.domain.VerificationToken;
import com.example.respository.GuestRepository;
import com.example.respository.GuestReservationRepository;
import com.example.respository.ReservationRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.VerificationTokenRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VerificationTokenRepositoryIntegrationTests {

	@Autowired
	VerificationTokenRepository repostitory;
	@Autowired
	GuestRepository guestRepository;
	@Autowired
	GuestReservationRepository guestReservationrepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	RestaurantRepository restaurantRepository;

	GuestReservation guestReservation;
	VerificationToken token;

	@Before
	public void setUp() {
		Restaurant restaurant = new Restaurant("proba", "proba");
		restaurant = restaurantRepository.save(restaurant);
		Reservation reservation = new Reservation(restaurant, "2017-05-08 15:00", "2017-05-08 17:00");
		reservation = reservationRepository.save(reservation);
		Guest guest = new Guest("novi@gmail.com", "123456", "proba", "proba1");
		guest = guestRepository.save(guest);
		guestReservation = new GuestReservation(guest, reservation, false);
		guestReservationrepository.save(guestReservation);
		token = new VerificationToken(guestReservation, UUID.randomUUID().toString());
		repostitory.save(token);
	}

	@Test
	public void get() {
		VerificationToken find = repostitory.findOne(token.getId());
		assertNotNull(find);
	}

	@Test
	public void getGuestReservationId() {
		Long id = repostitory.getGuestReservationId(token.getToken());
		assertEquals(guestReservation.getId(), id);
	}

	@Test
	public void getTokenId() {
		Long id = repostitory.getId(guestReservation.getId());
		assertEquals(token.getId(), id);
	}

}
