package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.respository.RestaurantRepository;
import com.example.respository.TableOfRestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableOfRestaurantRepositoryIntegrationTests {

	@Autowired
	TableOfRestaurantRepository repository;
	@Autowired
	RestaurantRepository restaurantRepository;

	@Test
	public void createTable() {
		Restaurant restaurant = new Restaurant("name", "location");
		Restaurant savedRestaurant = restaurantRepository.save(restaurant);
		TableOfRestaurant table = new TableOfRestaurant(1, 1, savedRestaurant);
		TableOfRestaurant savedTable = repository.save(table);
		assertEquals(table, savedTable);
	}

	@Test
	public void getTable() {
		Restaurant restaurant = new Restaurant("name", "location");
		Restaurant savedRestaurant = restaurantRepository.save(restaurant);
		TableOfRestaurant table = new TableOfRestaurant(1, 1, savedRestaurant);
		TableOfRestaurant savedTable = repository.save(table);
		TableOfRestaurant tableFind = repository.findOne(savedTable.getId());
		assertNotNull(tableFind);
	}
}
