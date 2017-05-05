package com.example.service;


import java.util.Collection;

import com.example.domain.Guest;


public interface GuestService {
	
	Guest getGuest(Long id);
	
	Guest addGuest(Guest guest);
	
	Collection<Guest> getAllGuests();
	
	boolean isExists(Long id);
	
	Guest updateGuest(Long id,Guest newGuest);
	
	void sendFriendRequest(Guest guest, Guest friend);
	
	void addFriend(Long id, Guest guest, Guest friend);
	
	Guest deleteFriend(Guest guest, Guest friend);
	
	Guest findByEmailAndPass(String email,String password);
	
	Collection<Guest> searchGuest(String search);

}
