package com.example.service;


import java.util.Collection;

import com.example.domain.Guest;


public interface GuestService {
	
	Guest getGuest(Long id);
	
	Guest addGuest(Guest guest);
	
	Collection<Guest> getAllGuests();
	
	boolean isExists(Long id);
	
	Guest updateGuest(Long id,Guest newGuest);

}
