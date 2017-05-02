package com.example.service;


import java.util.Collection;

import com.example.domain.Guest;


public interface GuestService {
	
	Guest getGuest(Long id);
	
	Guest addGuest(Guest guest);
	
	Collection<Guest> getAllGuests();
	
	boolean isExists(Long id);
	
	Guest updateGuest(Long id,Guest newGuest);
	
	Guest sendFriendRequest(Guest guest, Guest friend);
	
	Guest addFriend(Guest guest, Guest friend);
	
	Guest deleteFriend(Guest guest, Guest friend);

}
