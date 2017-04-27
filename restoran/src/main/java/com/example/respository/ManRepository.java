package com.example.respository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.example.domain.Employee;
import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;

import org.springframework.stereotype.Repository;


@Repository
public class ManRepository implements ManagerRepository {

	private Manager manager = null;
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

	@Override
	public Collection<Manager> findAll() {
		return this.managers.values();
	}

	@Override
	public Manager findOne(Long id) {
		return this.managers.get(id);
	}
	
	@Override
	public Manager findByEmail(String email){
		managers.put("email", new Manager("email","pw", "fn", "ln", null));
		for (Manager m : managers.values()) {
			if (m.getEmail().equalsIgnoreCase(email)){
				return m;
			}
		}
		System.out.println("eddig eljut");
		return null;
	}
	
	@Override
	public boolean isManagerExist(Manager man){
		return findByEmail(man.getEmail()) != null;
	}

	@Override
	public boolean changePassword(String newP, String oldP, Manager m) {
		if (managers.containsKey(m.getEmail())){
			if (managers.get(m.getEmail()).getPassword().equals(oldP)){
				managers.get(m.getEmail()).setPassword(newP);
				return true;
			}
		}
		return false;
	}

	@Override
	public void setLogedIn(Manager m) {
		this.manager=m;
		
	}

	@Override
	public Manager getLogedIn() {
		return this.manager;
	}


}
