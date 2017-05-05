package com.example.service;

import java.util.Collection;

import com.example.domain.Employee;
import com.example.domain.System_manager;
import com.example.domain.User;

public interface UserService {
	
	Collection<User> findAll();
	
	User findByEmail(String email);
	
	User addUser(User u);
	
	User changePass(User old, User pass);
	
	User login(User user);
	
	User getLogin();

	void logout();
}
