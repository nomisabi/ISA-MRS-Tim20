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
	
	void addFriend(Long guestId, Long friendId);
	
	boolean deleteFriend(Long guestId, Long friendId);
	
	Guest findByEmailAndPass(String email,String password);
	
	Collection<Guest> searchGuest(Long id,String search);
	
	Collection<Guest> findFriends(Long id);
	
	Collection<Guest> getRequests(Long id);
	
	Collection<Guest> searchFriends(Long id,String name);

}
