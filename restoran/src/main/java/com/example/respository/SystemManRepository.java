package com.example.respository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;

@Repository
public class SystemManRepository implements SystemManagerRepository {

	private System_manager sm = null;
	private final ConcurrentMap<String, Restaurant> restaurants = new ConcurrentHashMap<String, Restaurant>();
	private final ConcurrentMap<String, Manager> managers = new ConcurrentHashMap<String, Manager>();

	@Override
	public void signUP(System_manager sm) {
		this.sm = sm;
	}

	@Override
	public void createManager(Manager m, Restaurant r) {
		managers.put(m.getEmail(), m);

		restaurants.get(r.getName()).getManager().add(m);

	}

	@Override
	public Boolean login(System_manager m) {
		if (managers.containsKey(m.getEmail()))
				if (managers.get(m.getEmail()).getPassword().equals(m.getPassword())){
					this.sm=m;
					return true;
				}
		return false;
	}
	
	
}
