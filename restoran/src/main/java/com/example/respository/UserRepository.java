package com.example.respository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	//Collection<User> findAll();
	
	//User findByEmail(String email);
	
	

}
