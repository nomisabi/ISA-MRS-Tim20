package com.example.respository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;

import org.springframework.stereotype.Repository;


@Repository
public class ManRepository implements ManagerRepository {

	private final ConcurrentMap<String, Restaurant> restaurants = new ConcurrentHashMap<String, Restaurant>();
	private final ConcurrentMap<String, Manager> managers = new ConcurrentHashMap<String, Manager>();
	private final ConcurrentMap<String, Employee> employees = new ConcurrentHashMap<String, Employee>();
	private final ConcurrentMap<String, Supplier> suppliers = new ConcurrentHashMap<String, Supplier>();
	
	@Override
	public void createManager(Manager m) {
		managers.put(m.getEmail(), m);

		restaurants.get(m.getRestaurant().getName()).getManager().add(m);		
	}

	@Override
	public Employee createEmployee(Employee e) {
		if (!employees.containsKey(e.getEmail())){
			employees.put(e.getEmail(), e);
			return e;
		}
		return null;
	}

	@Override
	public Supplier createSupplier(Supplier s) {
		if (!suppliers.containsKey(s.getEmail())){
			suppliers.put(s.getEmail(), s);
			return s;
		}
		return null;
	}

}
