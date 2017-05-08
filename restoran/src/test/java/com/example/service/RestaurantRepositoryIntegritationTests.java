package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Restaurant;
import com.example.respository.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantRepositoryIntegritationTests {

	@Autowired
	RestaurantRepository repository;

	@Test
	public void getRestaurant() {
		Restaurant restaurant = new Restaurant("restaurant1", "ns");
		Restaurant savedRestaurant = repository.save(restaurant);
		Restaurant restaurantFind = repository.findOne(savedRestaurant.getId());
		assertNotNull(restaurantFind);
	}

	@Test
	public void createRestaurant() {
		Restaurant restaurant = new Restaurant("proba", "proba");
		Restaurant savedRestaurant = repository.save(restaurant);
		assertEquals(restaurant, savedRestaurant);
	}

}
