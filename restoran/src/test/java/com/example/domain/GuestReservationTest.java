package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GuestReservationTest {
	GuestReservation guestReservation;
	Guest guest;
	Reservation reservation;

	@Before
	public void setUp() {
		guest = new Guest("proba@proba.com", "123456");
		Restaurant restaurant = new Restaurant("proba", "proba");
		TableOfRestaurant table = new TableOfRestaurant(1, 5, restaurant);
		reservation = new Reservation(restaurant, table, "2017-05-15 12:00", "2017-05-15 14:00");
		guestReservation = new GuestReservation(guest, reservation);
	}

	@Test
	public void testGuest() {
		assertEquals(guest, guestReservation.getGuest());
	}

	@Test
	public void testReservation() {
		assertEquals(reservation, guestReservation.getReservation());
	}

	@Test
	public void testGuestReservationNotNull() {
		assertNotNull(guestReservation);
	}

}
