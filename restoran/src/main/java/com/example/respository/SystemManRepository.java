package com.example.respository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.example.domain.Manager;
import com.example.domain.System_manager;

import rs.ac.uns.ftn.informatika.jsp.domain.Greeting;

@Repository
public class SystemManRepository implements SystemManagerRepository {
	
	
	private System_manager sm = null;
	private final ConcurrentMap<String, Restaurant> restaurants = new ConcurrentHashMap<String, Restaurant>();
	private final ConcurrentMap<String, Manager> managers = new ConcurrentHashMap<String, Manager>();
	
	@Override
	public void signUP(System_manager sm){
		this.sm=sm;	
	}

	@Override
	public void createManager(Manager m, Restaurant r) {
		managers.put(m.getEmail(), m);
		restaurants.get(r.name).setManager(m);
		
	}
}
