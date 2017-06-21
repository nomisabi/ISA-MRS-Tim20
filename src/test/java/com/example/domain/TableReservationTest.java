package com.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TableReservationTest {
	Reservation reservation;
	TableOfRestaurant table;
	String startTime;
	String endTime;
	SimpleDateFormat sdf;
	TableReservation tableReservation;
	
	@Before
	public void setUp() {
		Restaurant restaurant = new Restaurant("Restoran1", "proba");
		table = new TableOfRestaurant(1, 1, restaurant);
		sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		Date date = new Date();
		startTime = sdf.format(date);
		date = new Date();
		endTime = sdf.format(date);
		reservation = new Reservation(restaurant, startTime, endTime);
		tableReservation = new TableReservation(table, reservation, startTime, endTime);
	}
	
	@Test
	public void testGetReservation() {
		assertEquals(reservation, tableReservation.getReservation());
	}

	@Test
	public void testGetStartTime() {
		assertEquals(startTime, tableReservation.getStartTime());
	}

	@Test
	public void testGetEndTime() {
		assertEquals(endTime, tableReservation.getEndTime());
	}
	
	@Test
	public void testGetTable() {
		assertEquals(table, tableReservation.getTable());
	}
	
	@Test
	public void testSetReservation() {
		Reservation r = new Reservation();
		tableReservation.setReservation(r);
		assertEquals(r, tableReservation.getReservation());
	}

	@Test
	public void testSetStartTime() {
		Date date = new Date();
		startTime = sdf.format(date);
		tableReservation.setStartTime(startTime);
		assertEquals(startTime, tableReservation.getStartTime());
	}

	@Test
	public void testSetEndTime() {
		Date date = new Date();
		endTime = sdf.format(date);
		tableReservation.setEndTime(endTime);
		assertEquals(endTime, tableReservation.getEndTime());
	}
	
	@Test
	public void testSetTable() {
		TableOfRestaurant t = new TableOfRestaurant();
		tableReservation.setTable(t);
		assertEquals(t, tableReservation.getTable());
	}
	
	@Test
	public void testTableReservationNotNull() {
		assertNotNull(tableReservation);
	}


}
