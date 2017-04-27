package com.example.service;

import java.util.Collection;

import com.example.domain.Employee;
import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Supplier;
import com.example.domain.System_manager;

public interface ManagerService {
	void createManager(Manager m);
	
	Employee createEmployee(Employee e);

	Supplier createSupplier(Supplier s);
	
	Collection<Manager> findAll();

	Manager findOne(Long id);

	Manager findByEmail(String email);

	boolean isManagerExist(Manager man);
	
	boolean changePassword(String newP, String oldP, Manager m);

	Manager getLogedIn();
	void setLogedIn(Manager m);
}
