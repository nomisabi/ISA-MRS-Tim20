package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.domain.Restaurant;
import com.example.respository.RestaurantRepository;

@Service
public class RestaurantServiceImp implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public Restaurant getRestaurant(Long id) {
		return restaurantRepository.findOne(id);
	}

	@Override
	public Collection<Restaurant> getAllRestaurants() {
		return (Collection<Restaurant>) restaurantRepository.findAll();
	}

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		Assert.notNull(restaurant, "Restaurant could not be null.");
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void updateMenu(Restaurant r) {
		restaurantRepository.updateMenu(r.getId(), r.getMenu().getId());
		
	}

	@Override
	public void insertEmpl(Long id_r, Long id_e) {
		restaurantRepository.insertEmpl(id_r, id_e);
		
	}

}
