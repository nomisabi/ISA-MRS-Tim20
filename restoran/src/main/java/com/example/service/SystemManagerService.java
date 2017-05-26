package com.example.service;

import java.util.Collection;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;

public interface SystemManagerService {
	
	Collection<System_manager> findAll();

	System_manager findOne(Long id);

	boolean isSysManagerExist(Long id);
	
	System_manager addSysMan(System_manager sm);
	
	Collection<Restaurant> findAllRest();

	Restaurant findOneRest(Long id);

	boolean isRestaurantExist(Long id);
	
	Restaurant addRestaurant(Restaurant r);
	 
	Restaurant update(Restaurant r);
	
	void update(System_manager man);
	
	void insertManager(Long id_r, Long id_m);


}
