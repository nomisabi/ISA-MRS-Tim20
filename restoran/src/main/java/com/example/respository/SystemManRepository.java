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
	public System_manager signUP(System_manager sm) {
		if (!system_managers.containsKey(sm.getEmail())){
				system_managers.put(sm.getEmail(), sm);
				return sm;
		}
		return null;
	}

	@Override
	public Manager createManager(Manager m) {

		if (!managers.containsKey(m.getEmail())){
			managers.put(m.getEmail(), m);
			return m;
		}
		return null;
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
		System_manager sm = new System_manager("1","2");
		system_managers.put("1", sm);
		return this.system_managers.values();
	}

	@Override
	public System_manager findOne(Long id) {
		return this.system_managers.get(id);
	}
	
	@Override
	public System_manager findByEmail(String email){
		system_managers.put("1",new System_manager("1","1"));
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

	@Override
	public void setLogedIn(System_manager sm){
		this.sm=sm;
	}

	@Override
	public System_manager getLogedIn() {
		return this.sm;
	}
	
}
