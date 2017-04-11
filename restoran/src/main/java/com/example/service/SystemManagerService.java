package com.example.service;

import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;


public interface SystemManagerService {
	void signUP(System_manager sm);
	void createManager(Manager sm, Restaurant r);
}
