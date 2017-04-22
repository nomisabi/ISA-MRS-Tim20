package com.example.respository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;

@Repository
public class SystemManRepository implements SystemManagerRepository {

	private System_manager sm = null;
	private final ConcurrentMap<String, Restaurant> restaurants = new ConcurrentHashMap<String, Restaurant>();
	private final ConcurrentMap<String, Manager> managers = new ConcurrentHashMap<String, Manager>();
	private final ConcurrentMap<String, System_manager> system_managers = new ConcurrentHashMap<String, System_manager>();

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
	public void createManager(System_manager m) {
		system_managers.put(m.getEmail(), m);

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
	

	@Override
	public Collection<System_manager> findAll() {
		return this.system_managers.values();
	}

	@Override
	public System_manager findOne(Long id) {
		return this.system_managers.get(id);
	}
	
	@Override
	public System_manager findByEmail(String email){
		for (System_manager sm : system_managers.values()) {
			if (sm.getEmail().equalsIgnoreCase(email)){
				return sm;
			}
		}
		return null;
	}
	
	@Override
	public boolean isSysManagerExist(System_manager sm){
		return findByEmail(sm.getEmail()) != null;
	}

	
	
}
