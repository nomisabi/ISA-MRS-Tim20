package com.example.respository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;
import com.example.domain.User;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
