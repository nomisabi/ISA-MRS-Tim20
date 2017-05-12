package com.example.service;

import java.util.Collection;

import com.example.domain.Restaurant;

public interface RestaurantService {
	
	Restaurant getRestaurant(Long id);
	
	Collection<Restaurant> getAllRestaurants();
	
	Restaurant createRestaurant(Restaurant restaurant);
	
	void updateMenu(Restaurant r);
	
	void insertEmpl(Long id_r, Long id_e);

	void updateDrinkMenu(Restaurant r);
}
