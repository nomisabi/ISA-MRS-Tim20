package com.example.respository;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import com.example.domain.Guest;

@Repository
public class GuestRepositoryImp implements GuestRepository {

	private final ConcurrentHashMap<String, Guest> guests = new ConcurrentHashMap<String, Guest>();

	@Override
	public void createGuest(Guest guest) {
		if (!guests.containsKey(guest.getEmail())) {
			guests.put(guest.getEmail(), guest);
		}

	}

}
