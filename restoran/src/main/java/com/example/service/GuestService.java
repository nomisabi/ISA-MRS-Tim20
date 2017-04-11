package com.example.service;

import com.example.domain.Guest;

public interface GuestService {
	void createGuest(Guest guest) throws Exception;
	boolean signUp(Guest guest) throws Exception;

}
