package com.example.respository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.example.domain.Manager;
import com.example.domain.Restaurant;

public class ManRepository implements ManagerRepository {

	private final ConcurrentMap<String, Restaurant> restaurants = new ConcurrentHashMap<String, Restaurant>();
	private final ConcurrentMap<String, Manager> managers = new ConcurrentHashMap<String, Manager>();
	
	@Override
	public void createManager(Manager m) {
		managers.put(m.getEmail(), m);
		//restaurants.get(r.name).getManager().Add(m);
		
	}

}
