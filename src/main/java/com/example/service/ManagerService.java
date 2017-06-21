package com.example.service;

import java.util.Collection;

import com.example.domain.Employee;
import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.System_manager;

public interface ManagerService {
	
	Manager createManager(Manager m);
	
	Employee createEmployee(Employee e);

	Supplier createSupplier(Supplier s);
	
	Collection<Manager> findAll();

	Manager findOne(Long id);
	
	void update(Manager man);

	//Manager findByEmail(String email);

	boolean isManagerExist(Long id);
	
	Restaurant findRest(Long id) ;
	
	void updateRest(Long id_r, Long id_s);

}
