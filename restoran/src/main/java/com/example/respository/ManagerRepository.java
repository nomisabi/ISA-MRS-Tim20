package com.example.respository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Supplier;
import com.example.domain.User;

public interface ManagerRepository extends CrudRepository<Manager, Long>{

	/*void createManager(Manager m);
	
	Employee createEmployee(Employee e);
	
	Supplier createSupplier(Supplier s);
	
	Collection<Manager> findAll();

	Manager findOne(Long id);

	Manager findByEmail(String email);

	boolean isManagerExist(Manager man);
	
	boolean changePassword(String newP, String oldP, Manager m);
	
	void setLogedIn(Manager m);
	
	Manager getLogedIn();*/
}
