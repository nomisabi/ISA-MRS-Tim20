package com.example.service;

import java.util.Collection;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;

public interface SystemManagerService {
	//System_manager signUP(System_manager sm);

	//Manager createManager(Manager sm);
	
	//Boolean login(System_manager m);
	//System_manager findByEmail(String email);
	
	Collection<System_manager> findAll();

	System_manager findOne(Long id);

	boolean isSysManagerExist(Long id);
	
	System_manager addSysMan(System_manager sm);
	
	Collection<Restaurant> findAllRest();

	Restaurant findOneRest(Long id);

	boolean isRestaurantExist(Long id);
	
	Restaurant addRestaurant(Restaurant r, Manager old, Manager new_m);
	
	//System_manager updateSysMan(Long id);
	
	//System_manager deleteSysMan(Long id);
	//void setLogedIn(System_manager sm);
	//System_manager getLogedIn();

}
