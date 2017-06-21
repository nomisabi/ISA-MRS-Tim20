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
		reservation = new Reservation(restaurant,"2017-05-15 12:00", "2017-05-15 14:00");
		guestReservation = new GuestReservation(guest, reservation);
	}

	@Test
	public void testGetGuest() {
		assertEquals(guest, guestReservation.getGuest());
	}

	@Test
	public void testGetReservation() {
		assertEquals(reservation, guestReservation.getReservation());
	}
	
	@Test
	public void testIsAccepted() {
		assertEquals(false, guestReservation.isAccepted());
	}
	
	@Test
	public void testIsRate() {
		assertEquals(false, guestReservation.isRate());
	}
	
	@Test
	public void testSetGuest() {
		Guest g = new Guest();
		guestReservation.setGuest(g);
		assertEquals(g, guestReservation.getGuest());
	}

	@Test
	public void testSetReservation() {
		Reservation r = new Reservation();
		guestReservation.setReservation(r);
		assertEquals(r, guestReservation.getReservation());
	}

	@Test
	public void testSetAccepted() {
		guestReservation.setAccepted(true);
		assertEquals(true, guestReservation.isAccepted());
	}
	
	@Test
	public void testSetRate() {
		guestReservation.setRate(true);
		assertEquals(true, guestReservation.isRate());
	}
	@Test
	public void testGuestReservationNotNull() {
		assertNotNull(guestReservation);
	}
	
	

}
