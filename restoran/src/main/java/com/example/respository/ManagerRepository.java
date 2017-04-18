package com.example.respository;

import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Supplier;

public interface ManagerRepository {

	void createManager(Manager m);
	
	Employee createEmployee(Employee e);
	
	Supplier createSupplier(Supplier s);
}
