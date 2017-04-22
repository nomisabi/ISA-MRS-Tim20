package com.example.service;

import java.util.Collection;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;

public interface SystemManagerService {
	void signUP(System_manager sm);

	void createManager(Manager sm, Restaurant r);
	
	Boolean login(System_manager m);
	
	Collection<System_manager> findAll();

	System_manager findOne(Long id);

	System_manager findByEmail(String email);

	boolean isSysManagerExist(System_manager m);

}
