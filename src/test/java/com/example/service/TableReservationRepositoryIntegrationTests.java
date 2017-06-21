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

import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.domain.TableReservation;
import com.example.respository.ReservationRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.TableOfRestaurantRepository;
import com.example.respository.TableReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableReservationRepositoryIntegrationTests {
	@Autowired
	TableReservationRepository repository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	TableOfRestaurantRepository tableOfRestaurantRepository;

	Reservation reservation;
	TableOfRestaurant tableOfRestaurant;
	TableReservation table;
	

	@Before
	public void setUp() {
		Restaurant restaurant = new Restaurant("proba", "proba");
		restaurant = restaurantRepository.save(restaurant);
		reservation = new Reservation(restaurant, "2017-05-08 15:00", "2017-05-08 17:00");
		reservation = reservationRepository.save(reservation);
		tableOfRestaurant = new TableOfRestaurant(1, 4, restaurant);
		tableOfRestaurant = tableOfRestaurantRepository.save(tableOfRestaurant);
		table = new TableReservation(tableOfRestaurant, reservation, reservation.getStartTime(), reservation.getEndTime());
		table = repository.save(table);
		
	}
	
	@Test
	public void getTable() {
		TableReservation t = repository.findOne(table.getId());
		assertNotNull(t);
	}
	
	@Test
	public void get() {
		Collection<TableReservation> t = repository.get(tableOfRestaurant.getId(), table.getStartTime(), table.getEndTime());
		assertNotEquals(0, t.size());
	}
	
	@Test
	public void getAllTableReservation() {
		Collection<TableOfRestaurant> t = repository.getAllTableReservation(reservation.getId());
		assertEquals(1, t.size());
	}

}
