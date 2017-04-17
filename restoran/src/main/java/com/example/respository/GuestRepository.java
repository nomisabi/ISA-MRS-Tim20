package com.example.respository;

import java.util.Collection;

import com.example.domain.Guest;

public interface GuestRepository {

	Guest createGuest(Guest guest);

	Collection<Guest> findAll();

	Guest findOne(Long id);
	
	Guest findByEmail(String email);
	
	boolean isGuestExist(Guest guest);

}
