package com.example.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ReservationTest {
	Reservation reservation;

	@Before
	public void setUp() {
		reservation = new Reservation();

	}

	@Test
	public void testReservationNotNull() {
		assertNotNull(reservation);
	}

}
