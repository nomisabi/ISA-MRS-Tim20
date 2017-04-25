package com.example.respository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;

@Repository
public class EmpRepository implements EmployeeRepository {

	private Employee emp = null;
	private final ConcurrentMap<String, Employee> employees = new ConcurrentHashMap<String, Employee>();



	@Override
	public Boolean login(Employee e) {
		if (employees.containsKey(e.getEmail()))
				if (employees.get(e.getEmail()).getPassword().equals(e.getPassword())){
					emp=e;
					return true;
				}
		return false;
	}
	

	@Override
	public Collection<Employee> findAll() {
		return this.employees.values();
	}

	@Override
	public Employee findOne(Long id) {
		return this.employees.get(id);
	}
	
	@Override
	public Employee findByEmail(String email){
		for (Employee em : employees.values()) {
			if (em.getEmail().equalsIgnoreCase(email)){
				return em;
			}
		}
		return null;
	}
	
	@Override
	public boolean isEmployeeExist(Employee em){
		return findByEmail(em.getEmail()) != null;
	}

	
	
}
