package com.example.service;

import java.util.Collection;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;

public interface SystemManagerService {
	//System_manager signUP(System_manager sm);

	//Manager createManager(Manager sm);
	
	//Boolean login(System_manager m);
	
	Collection<System_manager> findAll();

	System_manager findOne(Long id);

	//System_manager findByEmail(String email);

	boolean isSysManagerExist(Long id);
	
	System_manager addSysMan(System_manager sm);
	
	//System_manager updateSysMan(Long id);
	
	//System_manager deleteSysMan(Long id);
	//void setLogedIn(System_manager sm);
	//System_manager getLogedIn();

}
