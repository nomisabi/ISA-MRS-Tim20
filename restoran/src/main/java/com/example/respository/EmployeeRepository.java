package com.example.respository;

import java.util.Collection;

import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;

public interface EmployeeRepository {
	
	Boolean login(Employee e);
	
	Collection<Employee> findAll();

	Employee findOne(Long id);

	Employee findByEmail(String email);

	boolean isEmployeeExist(Employee em);
}
