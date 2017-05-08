package com.example.service;

import java.util.Collection;

import com.example.domain.Restaurant;

public interface RestaurantService {
	
	Restaurant getRestaurant(Long id);
	
	Collection<Restaurant> getAllRestaurants();
	
	Restaurant createRestaurant(Restaurant restaurant);
	
	
	

}
